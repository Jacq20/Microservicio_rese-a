package com.resena.Microservicio_resena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplatedConfig {
    @Bean 
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
