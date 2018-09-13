package com.example.demo;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=the",
        "wordCount.words.skip[1]=an",
        "wordCount.words.skip[2]=a",
})

@AutoConfigureMockMvc(secure=false)
public class WordCounterTest {
    @Autowired
    WordCounterConfigProperties countConfigProperties;

    @Test
    public void testWordCount() throws Exception {
        String input = "The brown cow jumps over the brown fox";

        WordCounter wordCounter = new WordCounter(countConfigProperties);
        wordCounter.count(input);

        Map<String, Integer> mapCount = mapValidate(input);

        assertThat(mapCount.size(), is(7));
        assertThat(mapCount, IsMapContaining.hasEntry("The",1));
        assertThat(mapCount, IsMapContaining.hasKey("brown"));
        assertThat(mapCount, IsMapContaining.hasValue(2));

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

    @Test
    public void testWordCountMethod() throws Exception {
        String input = "you never ever get, you never ever get";

        WordCounter counter = new WordCounter(countConfigProperties);

        Map<String, Integer> mapCount = counter.count(input);

        assertThat(mapCount.size(), is(4));

    }
}
