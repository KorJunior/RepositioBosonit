package com.example.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Block5LoggingApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(Block5LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);


		LOGGER.error("Esto es un mensaje de error"); // Log de nivel "ERROR"
		LOGGER.warn("Esto es un mensaje de advertencia"); // Log de nivel "WARNING"
		LOGGER.info("Esto es un mensaje informativo"); // Log de nivel "INFO"
		LOGGER.debug("DEBUG mola");
		LOGGER.trace("TRACE");

	}


}
