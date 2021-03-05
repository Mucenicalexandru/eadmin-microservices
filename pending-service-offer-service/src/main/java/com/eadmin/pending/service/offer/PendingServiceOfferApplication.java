package com.eadmin.pending.service.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PendingServiceOfferApplication {

    public static void main(String[] args) {
        SpringApplication.run(PendingServiceOfferApplication.class, args);
    }

}
