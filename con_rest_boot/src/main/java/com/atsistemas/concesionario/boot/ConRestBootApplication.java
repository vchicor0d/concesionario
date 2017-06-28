package com.atsistemas.concesionario.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Anotación que significa lo mismo que @Controller @EnableAutoConfiguration y @ComponentScan
@SpringBootApplication(scanBasePackages = {"com.atsistemas.concesionario.configuracion", "com.atsistemas.concesionario.controladores"})
public class ConRestBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConRestBootApplication.class, args);
	}
}
