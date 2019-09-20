package com.br.ms.communication.buytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationBuyTrip {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBuyTrip.class, args);
    }

}