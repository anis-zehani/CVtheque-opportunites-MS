package com.odix.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpportuniteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportuniteServiceApplication.class, args);
	}

}
