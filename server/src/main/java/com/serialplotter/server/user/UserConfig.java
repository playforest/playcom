package com.serialplotter.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {


    @Bean()
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        return args -> {
            User imran = new User(
                    "Imran",
                    "playforest0x@gmail.com",
                    UserRole.ADMIN,
                    LocalDateTime.of(
                            LocalDate.of(2022, Month.JANUARY, 21),
                            LocalTime.now()),
                    LocalDateTime.of(LocalDate.of(2022, Month.OCTOBER, 12),
                            LocalTime.now()),
                    LocalDateTime.now(), true,
                    false);

            User sarah = new User(
                    "Sarah",
                    "winocm@gmail.com",
                    UserRole.USER,
                    LocalDateTime.of(
                            LocalDate.of(2023, Month.FEBRUARY, 17),
                            LocalTime.now()),
                    LocalDateTime.of(LocalDate.of(2021, Month.APRIL, 9),
                            LocalTime.now()),
                    LocalDateTime.now(), true,
                    false);

            repository.saveAll(List.of(imran, sarah));
        };
    }
}
