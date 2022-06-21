package com.klimashin.celestial.body;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.klimashin.celestial.body")
@EnableEurekaClient
@EnableFeignClients
public class CelestialBodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CelestialBodyApplication.class);
    }
}
