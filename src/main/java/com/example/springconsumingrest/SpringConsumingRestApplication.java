package com.example.springconsumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@SpringBootApplication
public class SpringConsumingRestApplication {

    Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            ResponseEntity<Quote> response = restTemplate.getForEntity("http://localhost:8080/api/random", Quote.class);
            System.out.println("response = " + response.getBody());
            System.out.println("response = " + response);

            Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote.class);

            log.info("quote={}", quote);
        };
    }

}
