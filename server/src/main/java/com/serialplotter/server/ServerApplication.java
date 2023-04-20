package com.serialplotter.server;

import com.serialplotter.server.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@GetMapping("/")
	public List<User> hello() {
		return List.of(
				new User(
						1L,
						"playforest",
						"playforest0x@gmail.com",
						LocalDate.of(2023, Month.APRIL, 21),
						Boolean.TRUE)
		);
	}

}
