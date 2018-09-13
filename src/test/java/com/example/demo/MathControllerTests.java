package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MathController.class,MathService.class})
@AutoConfigureMockMvc(secure=false)
public class MathControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    public void testPiEndPoint()
     throws Exception
    {
        RequestBuilder request= get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
}
    @Test
    public void testCalculateEndPoint_add()
            throws Exception
    {
        RequestBuilder request= get("/math/calculate?operation=add&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("8"));
    }

    @Test
    public void testCalculateEndPoint_substract()
            throws Exception
    {
        RequestBuilder request= get("/math/calculate?operation=substract&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
    @Test
    public void testCalculateEndPoint_multiply()
            throws Exception
    {
        RequestBuilder request= get("/math/calculate?operation=multiply&x=5&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }
    @Test
    public void testCalculateEndPoint_divide()
            throws Exception
    {
        RequestBuilder request= get("/math/calculate?operation=divide&x=15&y=3");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    public void testSumEndPoint()
            throws Exception
    {
        RequestBuilder request= post("/math/sum?n=10&n=6&n=5&n=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("23"));
    }

    @Test
    public void testVolumeEndPoint()
            throws Exception
    {
        RequestBuilder request= post("/math/volume/10/10/10");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 10 x 10 x 10 rectangle is 1000"));
    }


    @Test
    public void testAreaEndPoint_Circle()
            throws Exception
    {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","circle")
                .param("radius","4");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.265482"));
}

    @Test
    public void testAreaEndPoint_rectangle()
            throws Exception
    {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","rectangle")
                .param("width","4")
                .param("height","6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4 x 6 rectangle is 24"));
    }

    @Test
    public void testAreaEndPoint_rectangle_Invalid()
            throws Exception
    {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","circle")
                .param("width","4")
                .param("height","6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void testAreaEndPoint_rectangle_Invalid_Zero_width()
            throws Exception
    {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","rectangle")
                .param("width","0")
                .param("height","6");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void testAreaEndPoint_circle_Invalid_Zero_radius()
            throws Exception
    {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","circle")
                .param("radius","0");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}
