package com.serialplotter.server.user;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDate lastlogin;
    private Boolean status;

    public User() {
    }

    public User(Long id, String name, String email, LocalDate lastlogin, Boolean stats) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
        this.status = stats;
    }

    public User(String name, String email, LocalDate lastlogin, Boolean stats) {
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
        this.status = stats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(LocalDate lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastlogin=" + lastlogin +
                ", status=" + status +
                '}';
    }
}
