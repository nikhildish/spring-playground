package com.example.demo;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(WordCounterController.class)
@AutoConfigureMockMvc(secure=false)
public class WordControllerTest {

    @MockBean
    WordCounter wordCounter;

    @Autowired
    MockMvc mvc;

    @Test
    public void testWordCount() throws Exception {
        String input = "The brown cow jumps over the brown fox";

        when(wordCounter.count(input)).thenReturn(mapValidate(input));

        MockHttpServletRequestBuilder request = post("/words/count/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);

        this.mvc.perform(request)
                .andExpect(status().isOk());
    }

    public Map<String, Integer> mapValidate(String input){
        Map<String, Integer> mapCount = new HashMap<>();
        mapCount.put("The",1);
        mapCount.put("over",1);
        mapCount.put("the",1);
        mapCount.put("jumps",1);
        mapCount.put("cow", 1);
        mapCount.put("brown", 2);
        mapCount.put("fox", 1);
        return mapCount;
    }






}