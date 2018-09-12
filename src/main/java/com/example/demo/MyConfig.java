package com.example.demo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("word-counter")
public class MyConfig {

    @Bean
    public WordCounter wordCounter() {
        return new WordCounter();
    }
}