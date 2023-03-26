package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis/streams")
public class StreamsController {
    private final RedisService redisService;

    @PostMapping
    public void setStream(String userId, String productId, String price) {
        Map map = new HashMap<String, String>();

        map.put("userId", userId);
        map.put("productId", productId);
        map.put("price", price);

        redisService.setStreams("order", map);
    }
}
