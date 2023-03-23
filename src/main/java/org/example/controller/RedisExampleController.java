package org.example.controller;

import lombok.RequiredArgsConstructor;

import org.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @Autowired
    RedisService redisService;

    @GetMapping("/string")
    public String getString(String key) {
        return redisService.getValues(key);
    }

    @PostMapping("/string")
    public void setString(String key, String value) {
        redisService.setValues(key, value);
    }

    @DeleteMapping("/string")
    public void deleteString(String key) {
        redisService.deleteValues(key);
    }
}
