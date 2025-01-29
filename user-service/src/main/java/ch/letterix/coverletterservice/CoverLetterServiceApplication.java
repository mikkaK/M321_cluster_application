package ch.letterix.coverletterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CoverLetterServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoverLetterServiceApplication.class, args);
	}

}
