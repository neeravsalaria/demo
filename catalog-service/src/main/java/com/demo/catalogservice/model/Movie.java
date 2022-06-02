package com.demo.catalogservice.model;

public class Movie {
    private String name;
    private String movieId;
    private String movieOverview;

    public Movie(String name, String movieId, String movieOverview) {
        this.name = name;
        this.movieId = movieId;
        this.movieOverview = movieOverview;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String desc) {
        this.movieId = desc;
    }
}
