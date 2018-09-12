package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public  class WordCounter {

    public WordCounter() {
    }

    public Map<String,Integer> count (String input) {
        Integer count = 0;
        input = input.replaceAll("\\p{Punct}", "");

        Map<String, Integer> countMap = new HashMap<String, Integer>();
        String[] splitString = input.split("\\s+");

        for (int i = 0; i < splitString.length; i++) {
            Integer n = countMap.get(splitString[i]);
            n = (n == null) ? 1 : ++n;
            countMap.put(splitString[i], n);
        }
        return countMap;
    }
}
