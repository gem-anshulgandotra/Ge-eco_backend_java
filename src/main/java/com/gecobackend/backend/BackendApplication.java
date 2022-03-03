package com.gecobackend.backend;


// server.port=8080
// spring.data.mongodb.uri=mongodb+srv://anshul1710:1234@cluster0.lhyeg.mongodb.net/BackendServices?retryWrites=true&w=majority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication 
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

	}

}
