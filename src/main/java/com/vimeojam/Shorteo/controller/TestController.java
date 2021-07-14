package com.vimeojam.Shorteo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import com.vimeojam.Shorteo.model.Test;
import com.vimeojam.Shorteo.service.TestService;
//mark class as Controller
@RestController
public class TestController
{
    //auto wire the TestService class
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
    private Test getTest(@PathVariable("testid") String testid)
    {
        return testService.getTestsById(testid);
    }

    //creating a delete mapping that deletes a specified test
    @DeleteMapping("/test/{testid}")
    private void deleteTest(@PathVariable("testid") String testid)
    {
        testService.delete(testid);
    }

    //creating post mapping that post the test detail in the database
    @PostMapping("/test")
    private String saveTest(@RequestBody Test test)
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

    @PostMapping("/url/create")
    private String create_shorteo_url(@RequestBody Test test){
        return testService.create_shorteo_url(test);
    }

    @GetMapping("/url/{shorteo_url}")
    private ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = testService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
