package org.example.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CacheService {
    // cacheNames::key 의 형태로 key 생성
    @Cacheable(cacheNames = "cache", key = "#id")
    // @CacheEvict 캐시 초기화
    // @CachePut 캐시 갱신, 메소드 실행에 영향을 주지않고 데이터 캐싱
    public HashMap<String, Object> getCache(int id) {
        HashMap<String, Object> tmp = new HashMap<String, Object>();

        try {
            Thread.sleep(500);
        }

        catch (InterruptedException e) {
        }

        tmp.put("id", id);
        tmp.put("title", "제목");
        tmp.put("content", "내용");

        return tmp;
    }
}
