package com.vimeojam.Shorteo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vimeojam.Shorteo.model.Test;
import com.vimeojam.Shorteo.repository.TestRepository;
//defining the business logic
@Service
public class TestService
{
    @Autowired
    TestRepository testRepository;
    //getting all books record by using the method findaAll() of CrudRepository
    public List<Test> getAllTests()
    {
        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return tests;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Test getTestsById(int id)
    {
        return testRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Test tests)
    {
        testRepository.save(tests);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        testRepository.deleteById(id);
    }
    //updating a record
    public void update(Test tests, int id)
    {
        testRepository.save(tests);
    }
}

