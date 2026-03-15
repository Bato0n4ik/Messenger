package com.andrew.messenger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class MessengerApplication {


	public static void main(String[] args) {

		var context = SpringApplication.run(MessengerApplication.class, args);
		System.out.println(context.getBean("liquibase"));
	}


}
