package org.ib.spring.vanillaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@SpringBootApplication
public class VanillaSpringBootApplication {
	public static void main(String[] args) {
	    //  FIX: Unable to start web server; nested exception is org.springframework.boot.web.server.WebServerException: Unable to create tempDir. java.io.tmpdir is set to <path>
		System.setProperty("java.io.tmpdir", Paths.get(".").toAbsolutePath().normalize().toString());
		SpringApplication.run(VanillaSpringBootApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "Hello Spring Boot!";
	}
}