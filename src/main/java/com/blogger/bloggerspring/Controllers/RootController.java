package com.blogger.bloggerspring.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Root Controller")
@RequestMapping("/")
public class RootController {

    @GetMapping()
    public String SendHelllo() {
        return "Hello from Spring Boot Application(v3.1.2) using Java(v17).";
    }

}
