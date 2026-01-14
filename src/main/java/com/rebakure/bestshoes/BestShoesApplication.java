package com.rebakure.bestshoes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@OpenAPIDefinition(
        info = @Info(
                title = "BestShoes API",
                version = "1.0.0",
                description = "Simple REST API built with Spring Boot for managing an imaginary shoe store called \"BestShoes\""
        )
)
@SpringBootApplication
@PropertySource("file:.env")
public class BestShoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestShoesApplication.class, args);
    }
}