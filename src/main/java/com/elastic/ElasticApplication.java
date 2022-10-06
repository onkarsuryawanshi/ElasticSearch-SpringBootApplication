package com.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ElasticApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ElasticApplication.class, args);
        System.out.println("Application Started!!!!");

    }

}
