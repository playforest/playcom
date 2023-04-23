package com.serialplotter.server.stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class StreamConfig {
    @Bean
    CommandLineRunner commandLineRunner(StreamRepository repository) {
        return args -> {
            Stream gyro = new Stream(
                    "gyro data",
                    "usb.modem1411.c",
                    1925000L,
                    "s3://bucket-name/mpu6050.csv",
                    1L,
                    LocalDateTime.now(),
                    false);

            Stream helloWorld = new Stream(
                    "playcom",
                    "dev/tty.stm32",
                    16000L,
                    "s3://bucket-name/stm32f10r8tc.csv",
                    1L,
                    LocalDateTime.now(),
                    false);


            repository.saveAll(List.of(gyro, helloWorld));
        };
    }
}
