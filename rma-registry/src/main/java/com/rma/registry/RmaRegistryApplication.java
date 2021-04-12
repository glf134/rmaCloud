package com.rma.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RmaRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmaRegistryApplication.class, args);
    }
}