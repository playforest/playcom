package com.serialplotter.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {


    public List<User> getUsers() {
        return List.of(
                new User(
                        1L,
                        "Imran",
                        "playforest0x@gmail.com",
                        LocalDate.of(2023, Month.JANUARY, 21),
                        LocalDate.of(2022, Month.OCTOBER, 12),
                        true
                )
        );
    }
}
