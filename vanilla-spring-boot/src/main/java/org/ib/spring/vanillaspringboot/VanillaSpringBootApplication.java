package org.ib.spring.vanillaspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
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

@Component
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        logger.info("JVM running for " + (ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0) + " sec");
        logger.warn("Forcing a normal application shutdown ...");
        System.exit(1);
        return;
    }
}