package com.vimeojam.Shorteo.controller;

import com.vimeojam.Shorteo.model.TestRedis;
import com.vimeojam.Shorteo.service.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//mark class as Controller
@RestController
public class TestRedisController {

    //autowire the TestService class
    @Autowired
    TestRedisService testRedisService;

    //creating a get mapping that retrieves all the tests detail from the database
    @GetMapping("/testredis")
    private List<TestRedis> getAllTests() {
        return testRedisService.getAllTests();
    }

    //creating a get mapping that retrieves the detail of a specific test
    @GetMapping("/testredis/{testid}")
    private TestRedis getTest(@PathVariable("testid") int testid) {
        return testRedisService.getTestsById(testid);
    }

    //creating a delete mapping that deletes a specified test
    @DeleteMapping("/testredis/{testid}")
    private void deleteTest(@PathVariable("testid") int testid) {
        testRedisService.delete(testid);
    }

    //creating post mapping that post the test detail in the database
    @PostMapping("/testredis")
    private int saveTest(@RequestBody TestRedis test) {
        testRedisService.saveOrUpdate(test);
        return test.getId();
    }

    //creating put mapping that updates the test detail
    @PutMapping("/testredis")
    private TestRedis update(@RequestBody TestRedis test) {
        testRedisService.saveOrUpdate(test);
        return test;
    }

    @GetMapping("/keys")
    private ResponseEntity validateKey(@RequestParam("key") String key) {
        if (testRedisService.validateKey(key))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
