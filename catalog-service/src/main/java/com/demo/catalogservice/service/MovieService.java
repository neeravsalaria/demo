package com.demo.catalogservice.service;

import com.demo.catalogservice.model.Movie;
import com.demo.catalogservice.model.Rating;
import com.demo.catalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultMovie")
    public Movie getMovie(Rating rr){
        return restTemplate.getForObject("http://MOVIE-SERVICE/movie/" + rr.getMovieId(), Movie.class);
    }


    public Movie getDefaultMovie(Rating rr) {
        return new Movie("No Movie","0","No");
    }


}
