package com.example.demo;

import com.example.demo.crud.Lesson;
import com.example.demo.crud.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by trainer3 on 4/9/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    private LessonRepository repository;

    @Autowired
    MockMvc mvc;

    String requestString = "{\"id\": 1, \"title\": \"cloud computing\"}";

    @Test
    @Transactional
    @Rollback
    public void testFindByTitle() throws Exception {
        String requestPost = "{\"title\": \"SQL-Server\",\"deliveredOn\": \"2012-06-09\"}";

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestPost);

        this.mvc.perform(request)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder request1 = get("/lessons/find/SQL-Server")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":3,\"title\":\"SQL-Server\",\"deliveredOn\":\"2012-06-09\"}"));

    }

    @Test
    @Transactional
    @Rollback
    public void testPostLesson() throws Exception{
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class) ));

    }

    @Test
    public void testGet() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring Security");
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(lesson.getId().intValue()) ));
    }

    @Test
    public void testGetById() throws Exception {


        MockHttpServletRequestBuilder request = get("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"Spring Security\",\"deliveredOn\":null}"));
    }

    @Test
    public void testPatch() throws Exception {
        String requestToPatch = "{\"title\": \"Spring Security\"}";

        Lesson lesson = new Lesson();
        lesson.setTitle("TEST");
        repository.save(lesson);

        MockHttpServletRequestBuilder request = patch("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestToPatch);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"Spring Security\",\"deliveredOn\":null}"));
    }
    @Test
    @Transactional
    @Rollback
    public void testGetInBetween() throws Exception {
        String requestString1 = "{\"id\": 1,\"title\": \"Dependency Injection\",\"deliveredOn\": \"2014-03-17\"}";
        String requestString2 = "{\"id\": 2,\"title\": \"Transactions\",\"deliveredOn\": \"2015-03-17\"}";

        MockHttpServletRequestBuilder request1 = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString1);

        this.mvc.perform(request1)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder request2 = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString2);

        this.mvc.perform(request2)
                .andExpect(status().isOk());

        MockHttpServletRequestBuilder request3 = get("/lessons/between?date1=2014-01-01&date2=2017-12-31")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request3)
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"title\":\"Dependency Injection\",\"deliveredOn\":\"2014-03-17\"},{\"id\":2,\"title\":\"Transactions\",\"deliveredOn\":\"2015-03-17\"}]"));
    }



}