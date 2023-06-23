package com.example.New.shop;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NewShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(NewShopApplication.class, args);
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);

	}

}
