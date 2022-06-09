package com.klimashin.spacecraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpacecraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacecraftApplication.class);
    }
}
