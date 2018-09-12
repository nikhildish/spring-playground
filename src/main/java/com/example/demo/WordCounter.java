package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public  class WordCounter {
    private final WordCounterConfigProperties configProperties;


    public WordCounter(WordCounterConfigProperties configProperties) {

        this.configProperties = configProperties;
    }

    public Map<String,Integer> count (String input) {
        Integer count = 0;
        input = input.replaceAll("\\p{Punct}", "");

        Map<String, Integer> countMap = new HashMap<String, Integer>();
        String[] splitString = input.split("\\s+");
        List<String> skipList = new ArrayList<>();
        skipList = configProperties.getWords().getSkip();
        boolean caseSensitive = configProperties.isCaseSensitive();

        for(int i=0; i<splitString.length; i++){
            if(caseSensitive == false){
                if(!(skipList.contains(splitString[i].toLowerCase()))){
                    Integer n = countMap.get(splitString[i]);
                    n = (n == null) ? 1 : ++n;
                    countMap.put(splitString[i], n);
                }
            }else{
                if(!(skipList.contains(splitString[i]))){
                    Integer n = countMap.get(splitString[i]);
                    n = (n == null) ? 1 : ++n;
                    countMap.put(splitString[i], n);
                }
            }
        }
        return countMap;
    }
}
