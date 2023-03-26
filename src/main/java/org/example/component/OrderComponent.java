package org.example.component;

import lombok.RequiredArgsConstructor;
import org.example.service.RedisService;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderComponent implements StreamListener<String, MapRecord<String, String, String>> {
    private final RedisService redisService;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        Map map = message.getValue();

        System.out.println(map);

        redisService.setStreams("payment", map);
    }
}
