package com.revconnect;

import com.revconnect.console.ConsoleUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RevconnectSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevconnectSpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner runConsole(ConsoleUI consoleUI) {
		return args -> {
			consoleUI.start();
		};
	}
}