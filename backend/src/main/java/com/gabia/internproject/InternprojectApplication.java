package com.gabia.internproject;

import com.gabia.internproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class InternprojectApplication {




	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled","false");
//		System.setProperty("spring.devtools.livereload.enabled","true");

		SpringApplication.run(InternprojectApplication.class, args);
//crossorgin 설정해주기

	}

}
