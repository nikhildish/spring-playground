package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
public class WordCounterTest {


    @Test
    public void testWordCountMethod() throws Exception {
        String input = "you never ever get, you never ever get";

        WordCounter counter = new WordCounter();

        Map<String, Integer> mapCount = counter.count(input);

        assertThat(mapCount.size(), is(4));

    }
}
