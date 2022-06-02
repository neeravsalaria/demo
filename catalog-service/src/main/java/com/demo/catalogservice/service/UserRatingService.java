package com.demo.catalogservice.service;


import com.demo.catalogservice.model.Rating;
import com.demo.catalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefaultRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + userId, UserRating.class);
    }

    private  UserRating getDefaultRating(String userId) {
        return new UserRating(Arrays.asList(new Rating("No Movie", 0)));
    }

}
