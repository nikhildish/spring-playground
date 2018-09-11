package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public  class WordCounterController {
    @Autowired
    WordCounter wordCounter;

    @PostMapping("/words/count")
    public @ResponseBody
    ResponseEntity<?> postWordCount(@RequestBody String input){
        Map<String, Integer> countMap = new HashMap<>();
        countMap = wordCounter.count(input);
        return new ResponseEntity<Object>(countMap, HttpStatus.OK);
    }
}

