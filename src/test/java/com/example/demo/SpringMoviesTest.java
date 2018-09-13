package com.example.demo;

import com.example.demo.MovieController;
import com.example.demo.MovieList;
import com.example.demo.Search;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import javax.validation.constraints.AssertTrue;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class SpringMoviesTest {

    private MovieService service;
    private MockRestServiceServer mockServer;

    @Before
    public void setUp(){
        MovieConfig config = mock(MovieConfig.class);
        when(config.getUrl()).thenReturn("http://www.omdbapi.com/");

        this.service = new MovieService(config);
        this.mockServer = MockRestServiceServer.createServer(service.getRestTemplate());
    }

    @Test
    public void testSpringMovieMock() throws Exception {
        String json = getJSON("/mockmoviedata.json");
        System.out.println(json);

        mockServer.expect(requestTo("http://www.omdbapi.com/?s=harry&apikey=90c623d3"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE)));

        ArrayList<Search> movies = service.getMovieDetailsFromOMDB("harry");

        assertThat(movies.get(0).getTitle(), equalTo("Harry Potter and the Deathly Hallows: Part 2"));
        assertThat(movies.get(0).getImdbID(), equalTo("tt1201607"));
        assertThat(movies.get(0).getPoster(), equalTo("https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2MTk3MDQ1N15BMl5BanBnXkFtZTcwMzI4NzA2NQ@@._V1_SX300.jpg"));
        assertThat(movies.get(0).getYear(), equalTo("2011"));
        assertThat(movies.get(0).getType(), equalTo(null));

        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}