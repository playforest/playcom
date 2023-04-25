package com.serialplotter.server.sync;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class SyncConfig {

    @Bean
    CommandLineRunner syncCommandLineRunner(SyncRepository repository) {
        return args -> {
            for (int i = 0; i < 200; i++) {
                Sync row = new Sync(1L,2L,"UPDATE", LocalDateTime.now(), "PENDING");
                repository.save(row);
            }
        };
    }
}
