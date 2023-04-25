package com.verter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PrimoRegistry {
    public static void main(String[] args) {
        SpringApplication.run(PrimoRegistry.class, args);
    }
}