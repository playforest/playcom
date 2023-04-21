package com.serialplotter.server.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name="users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate lastlogin;
    private LocalDate createdDate;
    private Boolean status;

    public User() {

    }

    public User(Long id, String name, String email, LocalDate lastlogin, LocalDate createdDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
        this.createdDate = createdDate;
        this.status = status;
    }

    public User(String name, String email, LocalDate lastlogin, LocalDate createdDate, Boolean status) {
        this.name = name;
        this.email = email;
        this.lastlogin = lastlogin;
        this.createdDate = createdDate;
        this.status = status;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
                ", createdDate=" + createdDate +
                ", status=" + status +
                '}';
    }
}
