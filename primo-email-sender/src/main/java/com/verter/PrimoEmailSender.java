package com.verter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PrimoEmailSender {
    public static void main(String[] args) {
        SpringApplication.run(PrimoEmailSender.class, args);
    }
}