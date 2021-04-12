package com.rma.system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableDistributedTransaction //基于LCN分布式事务
@EnableFeignClients
public class RamSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RamSystemApplication.class, args);
    }
}