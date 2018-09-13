package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Search {

    private String title;

    @JsonProperty(value = "title")
    public String getTitle() { return this.title; }

    @JsonProperty(value = "Title")
    public void setTitle(String title) { this.title = title; }

    private String year;

    @JsonProperty(value = "year")
    public String getYear() { return this.year; }

    @JsonProperty(value = "Year")
    public void setYear(String year) { this.year = year; }

    private String imdbID;

    public String getImdbID() { return this.imdbID; }

    public void setImdbID(String imdbID) { this.imdbID = imdbID; }

    private String type;

    @JsonIgnore
    @JsonProperty(value = "Type")
    public String getType() { return this.type; }

    public void setType(String Type) { this.type = Type; }

    private String poster;

    @JsonProperty(value = "poster")
    public String getPoster() { return this.poster; }

    @JsonProperty(value = "Poster")
    public void setPoster(String Poster) { this.poster = Poster; }

}
