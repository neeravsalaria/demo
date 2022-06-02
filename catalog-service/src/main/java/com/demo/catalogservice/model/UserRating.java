package com.demo.catalogservice.model;

import java.util.List;

public class UserRating {
    private List<Rating> movieRatingList;

    public UserRating() {
    }

    public UserRating(List<Rating> movieRatingList) {
        this.movieRatingList = movieRatingList;
    }

    public List<Rating> getMovieRatingList() {
        return movieRatingList;
    }

    public void setMovieRatingList(List<Rating> movieRatingList) {
        this.movieRatingList = movieRatingList;
    }

}
