package cn.hpp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BettlApplication {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BettlApplication.class);
		application.run(args);
	}
}

