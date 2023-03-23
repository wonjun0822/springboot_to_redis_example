package org.example.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    public String getValues(String key){
        ValueOperations ops = redisTemplate.opsForValue();

        return ops.get(key).toString();
    }

    public void setValues(String key, String value){
        ValueOperations ops = redisTemplate.opsForValue();

        ops.set(key,value, 60, TimeUnit.SECONDS);
    }

    public void deleteValues(String key) {
        ValueOperations ops = redisTemplate.opsForValue();

        ops.getOperations().delete(key);
    }

    public Set getSets(String key) {
        SetOperations ops = redisTemplate.opsForSet();

        return ops.members(key);
    }

    public void setSets(String key, String value) {
        SetOperations<String, Object> ops = redisTemplate.opsForSet();

        ops.add(key, value, 60, TimeUnit.SECONDS);
    }

    public void deleteSets(String key, String value) {
        SetOperations ops = redisTemplate.opsForSet();

        ops.remove(key, value);
    }

    public Object getLists(String key, int index) {
        ListOperations ops = redisTemplate.opsForList();

        return ops.index(key, index);
    }

    public void setLists(String key, String value) {
        ListOperations ops = redisTemplate.opsForList();

        ops.leftPush(key, value);

        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
    }

    public void deleteLists(String key) {
        ListOperations ops = redisTemplate.opsForList();

        ops.rightPop(key);
    }

    public Object getHashes(String key, String field) {
        HashOperations ops = redisTemplate.opsForHash();

        return ops.get(key, field);
    }

    public void setHashes(String key, String field, String value) {
        HashOperations ops = redisTemplate.opsForHash();

        ops.put(key, field, value);
    }

    public void deleteHashes(String key, String field) {
        HashOperations ops = redisTemplate.opsForHash();

        ops.delete(key, field);
    }

    public List<String> getSortedSets(String key) {
        ZSetOperations ops = redisTemplate.opsForZSet();

        return new ArrayList<>(ops.reverseRange(key, 0, 4));
    }

    public void setSortedSets(String key, String value, int score) {
        ZSetOperations ops = redisTemplate.opsForZSet();

        ops.add(key, value, score);
    }

    public void deleteSortedSets(String key, String value) {
        ZSetOperations ops = redisTemplate.opsForZSet();

        ops.remove(key, value);
    }
}