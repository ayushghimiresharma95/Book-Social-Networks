package com.example.BookNetworkServer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.BookNetworkServer.Role.Role;
import com.example.BookNetworkServer.Role.RoleRepositary;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BookNetworkServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepositary roleRepositary){
		return args -> {
			if (roleRepositary.findByName("USER").isEmpty()){
				roleRepositary.save(Role.builder().name("USER").build());
			}
		} ;
	}

}
