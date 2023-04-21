package com.serialplotter.server.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User imran = new User(
                    "Imran",
                    "playforest0x@gmail.com",
                    "Admin",
                    LocalDate.of(2023, Month.JANUARY, 21),
                    LocalDate.of(2022, Month.OCTOBER, 12),
                    true
            );

            User sarah = new User(
                    "Sarah",
                    "winocm@gmail.com",
                    "Viewer",
                    LocalDate.of(2023, Month.FEBRUARY, 17),
                    LocalDate.of(2021, Month.APRIL, 9),
                    true
            );

            repository.saveAll(List.of(imran, sarah));
        };
    }
}
