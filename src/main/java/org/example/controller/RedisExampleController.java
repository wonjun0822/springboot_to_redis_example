package org.example.controller;

import org.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisExampleController {
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

    @GetMapping("/set")
    public Set getSet(String key) {
        return redisService.getSets(key);
    }

    @PostMapping("/set")
    public void setSet(String key, String value) {
        redisService.setSets(key, value);
    }

    @DeleteMapping("/set")
    public void deleteSet(String key, String value) {
        redisService.deleteSets(key, value);
    }

    @GetMapping("/list")
    public Object getList(String key, @RequestParam(defaultValue = "0") int index) {
        return redisService.getLists(key, index);
    }

    @PostMapping("/list")
    public void setList(String key, String value) {
        redisService.setLists(key, value);
    }

    @DeleteMapping("/list")
    public void deleteList(String key) {
        redisService.deleteLists(key);
    }

    @GetMapping("/hash")
    public Object getHash(String key, String field) {
        return redisService.getHashes(key, field);
    }

    @PostMapping("/hash")
    public void setList(String key, String field, String value) {
        redisService.setHashes(key, field, value);
    }

    @DeleteMapping("/hash")
    public void deleteHash(String key, String field) {
        redisService.deleteHashes(key, field);
    }

    @GetMapping("/sortedset")
    public List<String> getSortedSet(String key) {
        return redisService.getSortedSets(key);
    }

    @PostMapping("/sortedset")
    public void setSortedSet(String key, String value, @RequestParam(defaultValue = "0") int score) {
        redisService.setSortedSets(key, value, score);
    }

    @DeleteMapping("/sortedset")
    public void deleteSortedSet(String key, String value) {
        redisService.deleteSortedSets(key, value);
    }
}
