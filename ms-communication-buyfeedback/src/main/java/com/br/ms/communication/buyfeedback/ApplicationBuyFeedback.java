package com.br.ms.communication.buyfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableDiscoveryClient
public class ApplicationBuyFeedback {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ApplicationBuyFeedback.class, args);
    }

}
