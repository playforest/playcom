package com.serialplotter.server.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    private String role;
    private LocalDate lastLogin;
    @Transient
    private Long daysSinceLastLogin;
    private LocalDate createdDate;
    private Boolean status;
    private Boolean isDeleted;

    public User() {

    }

    public User(Long id, String name, String email, String role,
                LocalDate lastlogin, LocalDate createdDate, Boolean status, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.lastLogin = lastlogin;
        this.createdDate = createdDate;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public User(String name, String email, String role, LocalDate lastlogin,
                LocalDate createdDate, Boolean status, Boolean isDeleted) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.lastLogin = lastlogin;
        this.createdDate = createdDate;
        this.status = status;
        this.isDeleted = isDeleted;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastlogin() {
        return lastLogin;
    }

    public void setLastlogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getDaysSinceLastLogin() {
        LocalDate currentDate = LocalDate.now();
        this.daysSinceLastLogin = ChronoUnit.DAYS.between(this.lastLogin, currentDate);
        return daysSinceLastLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", lastLogin=" + lastLogin +
                ", daysSinceLastLogin=" + daysSinceLastLogin +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }


}
