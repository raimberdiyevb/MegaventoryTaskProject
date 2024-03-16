package com.example.TaskProject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskProjectApplication {

	public static String API_KEY = "cfe543d2c31f2b29@m146529";
	public static String BASE_URL = "https://api.megaventory.com/v2017a";
	public static void main(String[] args) {
		SpringApplication.run(TaskProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(){

		return args -> {
			System.out.println(API_KEY);
			System.out.println(BASE_URL);
        };
    }
}
