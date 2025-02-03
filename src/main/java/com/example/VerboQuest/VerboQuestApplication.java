package com.example.VerboQuest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VerboQuestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerboQuestApplication.class, args);
	}
}
