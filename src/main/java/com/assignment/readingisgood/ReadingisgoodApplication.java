package com.assignment.readingisgood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@ComponentScan("com.assignment.readingisgood")
@SpringBootApplication
public class ReadingisgoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingisgoodApplication.class, args);
	}

}
