package com.agharibi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringmvcApplication.class, args);


//		System.out.println("*********  Beans count -> " + applicationContext.getBeanDefinitionCount() + "  ********");
//		System.out.println();
//
//		for(String name : applicationContext.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}


	}
}
