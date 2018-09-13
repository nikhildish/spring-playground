package com.example.demo;

import com.example.demo.MovieList;
import com.example.demo.Search;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


@Service
public class MovieService {

    private final MovieConfig config;

    private final RestTemplate restTemplate = new RestTemplate();

    public MovieService(MovieConfig config) {
        this.config = config;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public ArrayList<Search> getMovieDetailsFromOMDB(String q) throws UnsupportedEncodingException {
        return this.restTemplate.getForObject(String.format("%s/?s=%s%s", this.config.getUrl(), q,"&apikey=90c623d3"), MovieList.class).getSearch();
    }

}
