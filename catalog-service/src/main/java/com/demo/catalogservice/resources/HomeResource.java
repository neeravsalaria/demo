package com.demo.catalogservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/") 
    private String home(){
        return "Welcome";
    }
    
    @GetMapping("/user")
    private String user() {
        return ("Welcome User");
    }

    @GetMapping("/admin")
    private String admin() {
        return ("Welcome Admin");
    }
}
