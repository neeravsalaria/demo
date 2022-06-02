package com.demo.catalogservice.resources;

import com.demo.catalogservice.configuration.Dbsettings;
import com.demo.catalogservice.model.CatalogItem;
import com.demo.catalogservice.model.Movie;
import com.demo.catalogservice.model.Rating;
import com.demo.catalogservice.model.UserRating;
import com.demo.catalogservice.service.MovieService;
import com.demo.catalogservice.service.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.security.core.context.SecurityContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Value("${app.greeting}")
    private String greeting;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webclinetBuilder;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    private Dbsettings dbsettings;


    @GetMapping("/welcome")
    public String welcomeUrl() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return " Welcome Scopes: " + authentication.getAuthorities();
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return greeting+",dbsettings = "+dbsettings.getConnection()+", table=  "+dbsettings.getTable();
    }

    @GetMapping("/{userId}")
   // @HystrixCommand(fallbackMethod = "getCatalogFallback")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId) {

        //get all movieIds
        return userRatingService.getUserRating(userId).getMovieRatingList().stream().map(rr ->
        {
            Movie mv =movieService.getMovie(rr);

//            Movie mv2 = webclinetBuilder.build().get().uri("http://MOVIE-SERVICE/movie/" + rr.getMovieId())
//                    .retrieve().bodyToMono(Movie.class).block();

            return new CatalogItem(mv.getName(), "abc", rr.getRating());
        }).collect(Collectors.toList());

        //call the movie info service to get moview info from the movie id

        //for each movieid call the rating service to get the rating.

        //put them together to share the list
        //return Collections.singletonList(new CatalogItem("Transformers", "abc", 4));
    }
    //    public List<CatalogItem> getCatalogFallback(@PathVariable("userId")  String userId) {
//        return Collections.singletonList(new CatalogItem("No Movie", "No desc", 0));
//    }
}
