package com.rcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcgApplication.class, args);
	}

}
