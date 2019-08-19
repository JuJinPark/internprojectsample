package com.gabia.internproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;


import java.util.Arrays;


@SpringBootApplication

public class InternprojectApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext context;



	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","false");
		System.setProperty("spring.devtools.livereload.enabled","true");
		SpringApplication.run(InternprojectApplication.class, args);

	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	public void run(String... args) throws Exception{
		String[] beans = context.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}

	}





}


