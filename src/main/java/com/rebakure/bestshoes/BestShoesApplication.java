package com.rebakure.bestshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")
public class BestShoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestShoesApplication.class, args);
    }
}