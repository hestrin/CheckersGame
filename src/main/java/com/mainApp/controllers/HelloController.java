package com.mainApp.controllers;

/**
 * Created by mateusz.malinowski on 09.10.2018.
 */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Spring Boot starting app";
    }

}