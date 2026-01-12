package com.example.PortfolioV1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PortfolioV1Application {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioV1Application.class, args);
	}

}
