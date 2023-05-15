package com.example.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5PropertiesApplication {
	@Value("${greeting}")
	private String valorPropiedad1;

	@Value("${my.number}")
	private String valorPropiedad2;

	@Value("${new.property:new.property no tiene valor}")
	private String valorPropiedad3;

	public static void main(String[] args) {
		SpringApplication.run(Block5PropertiesApplication.class, args);
	}
	@Bean
	public void paso1(){
		System.out.println("El valor de greeting es:: " +valorPropiedad1);
		System.out.println("El valor de my.number es: " + valorPropiedad2);
		System.out.println("El valor de new.property es: " + valorPropiedad3);
	}

}
