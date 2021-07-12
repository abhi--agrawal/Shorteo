package com.vimeojam.Shorteo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vimeojam.Shorteo.model.Test;
import com.vimeojam.Shorteo.service.TestService;
//mark class as Controller
@RestController
public class TestController
{
    //autowire the TestService class
    @Autowired
    TestService testService;
    //creating a get mapping that retrieves all the tests detail from the database
    @GetMapping("/test")
    private List<Test> getAllTests()
    {
        return testService.getAllTests();
    }
    //creating a get mapping that retrieves the detail of a specific test
    @GetMapping("/test/{testid}")
    private Test getTest(@PathVariable("testid") int testid)
    {
        return testService.getTestsById(testid);
    }
    //creating a delete mapping that deletes a specified test
    @DeleteMapping("/test/{testid}")
    private void deleteTest(@PathVariable("testid") int testid)
    {
        testService.delete(testid);
    }
    //creating post mapping that post the test detail in the database
    @PostMapping("/test")
    private int saveTest(@RequestBody Test test)
    {
        testService.saveOrUpdate(test);
        return test.getId();
    }
    //creating put mapping that updates the test detail
    @PutMapping("/test")
    private Test update(@RequestBody Test test)
    {
        testService.saveOrUpdate(test);
        return test;
    }
}
