package com.memo.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemoTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoTripApplication.class, args);
	}

}
