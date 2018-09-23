package org.ib.spring.vanillaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class VanillaSpringBootApplication {
	public static void main(String[] args) {
        SpringApplication.run(VanillaSpringBootApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "Hello Spring Boot!";
	}
}