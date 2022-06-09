package com.klimashin.orbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrbitApplication.class, args);

    }
}
