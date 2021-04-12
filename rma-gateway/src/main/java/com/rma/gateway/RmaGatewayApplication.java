package com.rma.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RmaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RmaGatewayApplication.class, args);
    }
}