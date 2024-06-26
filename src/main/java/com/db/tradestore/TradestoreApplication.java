package com.db.tradestore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradestoreApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradestoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TradestoreApplication.class, args);
		LOGGER.info("Tradestore running...");
	}
}
