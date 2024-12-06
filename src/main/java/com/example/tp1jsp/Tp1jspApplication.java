package com.example.tp1jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaRepositories("com.example.tp1jsp")
public class Tp1jspApplication {

    public static void main(String[] args) {SpringApplication.run(Tp1jspApplication.class, args);}

    @GetMapping("/hello-world")
    public String helloworld() {
        return "Hello World";
    }

}
