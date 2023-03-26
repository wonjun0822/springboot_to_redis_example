package org.example.config;

import org.example.component.OrderComponent;
import org.example.component.PaymentComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

@Configuration
public class RedisStreamConfig {
    @Autowired
    private OrderComponent orderComponent;

    @Autowired
    private PaymentComponent paymentComponent;

    @Bean
    public Subscription orderSubscription(RedisConnectionFactory factory) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .build();

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory, options);

        // XGROUP CREATE order group1 $ MKSTREAM
        Subscription subscription = listenerContainer.receiveAutoAck(
                Consumer.from("group1", "instance-1"),
                StreamOffset.create("order", ReadOffset.lastConsumed()),
                orderComponent
        );

        listenerContainer.start();

        return subscription;
    }

    @Bean
    public Subscription paymentSubscription(RedisConnectionFactory factory) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .build();

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory, options);

        // XGROUP CREATE payment group1 $ MKSTREAM
        Subscription subscription = listenerContainer.receiveAutoAck(
                Consumer.from("group1", "instance-1"),
                StreamOffset.create("payment", ReadOffset.lastConsumed()),
                paymentComponent
        );

        listenerContainer.start();

        return subscription;
    }
}
