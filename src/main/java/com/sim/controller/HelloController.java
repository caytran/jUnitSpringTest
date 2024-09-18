package com.sim.controller;

import com.sim.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    @GetMapping
    public String sayHelloWorld() {
        return "Hello World from Inside Spring Boot App";
    }
}
