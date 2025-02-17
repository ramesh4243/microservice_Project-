package com.microservice.post.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  // This annotation makes RestTemplate load-balanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
