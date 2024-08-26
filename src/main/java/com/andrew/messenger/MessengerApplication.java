package com.andrew.messenger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerApplication {


	public static void main(String[] args) {
		var context = SpringApplication.run(MessengerApplication.class, args);
	}


}
