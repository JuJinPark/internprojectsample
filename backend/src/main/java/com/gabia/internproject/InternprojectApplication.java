package com.gabia.internproject;

import com.gabia.internproject.data.repository.UserRepository;
import com.gabia.internproject.service.DataBaseUserService;
import com.gabia.internproject.service.HiworksUserService;
import com.gabia.internproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class InternprojectApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext context;


	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled","false");
//		System.setProperty("spring.devtools.livereload.enabled","true");

		SpringApplication.run(InternprojectApplication.class, args);




	}



	public void run(String... args) throws Exception{
//		String[] beans = context.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String bean : beans) {
//			System.out.println(bean);
//		}
//
//		System.out.println(context.getBean(HiworksUserService.class));
//		System.out.println(context.getBean(DataBaseUserService.class));

	}



}
