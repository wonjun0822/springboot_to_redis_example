package org.example.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

    public String getValues(String key){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        return ops.get(key);
    }

    public void setValues(String key, String value){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.set(key,value);
    }

    public void deleteValues(String key) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.getOperations().delete(key);
    }

    public Set getSets(String key) {
        SetOperations<String, Object> ops = redisTemplate.opsForSet();

        return ops.members(key);
    }

    public void setSets(String key, String value) {
        SetOperations<String, Object> ops = redisTemplate.opsForSet();

        ops.add(key, value);
    }

    public void deleteSets(String key, String value) {
        SetOperations<String, Object> ops = redisTemplate.opsForSet();

        ops.remove(key, value);
    }

    public Object getLists(String key, int index) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();

        return ops.index(key, index);
    }

    public void setLists(String key, String value) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();

        ops.leftPush(key, value);
    }

    public void deleteLists(String key) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();

        ops.rightPop(key);
    }

    public Object getHashes(String key, String field) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

        return ops.get(key, field);
    }

    public void setHashes(String key, String field, String value) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

        ops.put(key, field, value);
    }

    public void deleteHashes(String key, String field) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

        ops.delete(key, field);
    }
}