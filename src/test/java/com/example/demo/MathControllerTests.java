package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MathController.class,MathService.class})
public class MathControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    public void testPiEndPoint()
     throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
}
    @Test
    public void testCalculateEndPoint_add()
            throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=add&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("8"));
    }

    @Test
    public void testCalculateEndPoint_substract()
            throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=substract&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
    @Test
    public void testCalculateEndPoint_multiply()
            throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }
    @Test
    public void testCalculateEndPoint_divide()
            throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=15&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    public void testSumEndPoint()
            throws Exception
    {
        RequestBuilder request= MockMvcRequestBuilders.post("/math/sum?n=10&n=6&n=5&n=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("23"));
    }
}
