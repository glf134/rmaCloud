package com.rma.real;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glf
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RmaRealApplication {
    public static void main(String[] args) {
        SpringApplication.run(RmaRealApplication.class, args);
    }
}
