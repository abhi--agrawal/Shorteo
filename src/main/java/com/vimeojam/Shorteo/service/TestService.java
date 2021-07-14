package com.vimeojam.Shorteo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.vimeojam.Shorteo.model.TestRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vimeojam.Shorteo.model.Test;
import com.vimeojam.Shorteo.repository.TestRepository;

import javax.persistence.EntityNotFoundException;

//defining the business logic
@Service
public class TestService
{
    @Autowired
    TestRepository testRepository;
    @Autowired
    BaseConversion conversion;
    @Autowired
    TestRedisService testRedisService;
    //getting all test record by using the method findAll() of CrudRepository
    public List<Test> getAllTests()
    {
        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return tests;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Test getTestsById(String id)
    {
        return testRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Test tests)
    {
        testRepository.save(tests);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(String id)
    {
        testRepository.deleteById(id);
    }
    //updating a record
    public void update(Test tests, int id)
    {
        testRepository.save(tests);
    }
    public String create_shorteo_url(Test test){
        Test newTest = new Test();
        UUID uuid = UUID.randomUUID();
        String shorteo_url = "";
        newTest.setId(uuid.toString());
        newTest.setOriginal_url(test.getOriginal_url());
        newTest.setCreated_at(new Date());
        if(test.getCustom_url() != null){
            newTest.setCustom_url(test.getCustom_url());
            shorteo_url = test.getCustom_url();
        }
        var entity = testRepository.save(newTest);
        if(shorteo_url.isEmpty()) {
            shorteo_url = conversion.encode(UUID.fromString(entity.getId()));
        }
        TestRedis testRedis = new TestRedis();
        testRedis.setShorteo_url(shorteo_url);
        testRedisService.saveOrUpdate(testRedis);
        return shorteo_url;
    }

    public String getOriginalUrl(String shorteo_url) {
        var id = conversion.decode(shorteo_url);
        var entity = testRepository.findById(id.toString())
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shorteo_url));
        return entity.getOriginal_url();
    }
}

