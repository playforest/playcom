package com.serialplotter.server.user;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDate lastlogin;

    public User() {
    }

    public User(Long id, String name, String email, LocalDate lastlogin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
    }

    public User(String name, String email, LocalDate lastlogin) {
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
    }
}
