package com.example.eadmin.poll.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PollServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollServiceApplication.class, args);
    }

}
