package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheExampleController {
    @Autowired
    private final CacheService cacheService;

    @GetMapping("/{id}")
    public HashMap<String, Object> getCache(@PathVariable int id) {
        return cacheService.getCache(id);
    }
}
