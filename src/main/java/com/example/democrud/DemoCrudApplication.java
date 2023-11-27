package com.example.democrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.example")
@ComponentScan("com.example")
public class DemoCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoCrudApplication.class, args);
	}
}


