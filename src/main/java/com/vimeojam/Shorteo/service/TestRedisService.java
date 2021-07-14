package com.vimeojam.Shorteo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vimeojam.Shorteo.model.TestRedis;
import com.vimeojam.Shorteo.repository.TestRedisRepository;
import com.vimeojam.Shorteo.configuration.RedisConfig;
import org.springframework.data.redis.core.SetOperations;

import javax.annotation.Resource;

@Service
public class TestRedisService {
    @Autowired
    TestRedisRepository testRedisRepository;

    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOps;

    //getting all books record by using the method findaAll() of CrudRepository
    public List<TestRedis> getAllTests()
    {
        List<TestRedis> tests = new ArrayList<>();
        testRedisRepository.findAll().forEach(tests::add);
        return tests;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public TestRedis getTestsById(int id)
    {
        return testRedisRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(TestRedis tests)
    {
        setOps.add(RedisConfig.KEY_SET, tests.getShorteo_url());
        testRedisRepository.save(tests);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        testRedisRepository.deleteById(id);
    }
    //updating a record
    public void update(TestRedis tests, int id)
    {
        testRedisRepository.save(tests);
    }

    public boolean validateKey(String key) {
        return setOps.isMember(RedisConfig.KEY_SET, key);
    }
}
