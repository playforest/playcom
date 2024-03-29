package com.serialplotter.server.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {
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
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private LocalDateTime lastLogin;
    @Transient
    private Long daysSinceLastLogin;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean isDeleted = false;

    public User() {

    }

    public User(Long id, String username, String email, UserRole role,
                LocalDateTime lastLogin, LocalDateTime createdDate, LocalDateTime lastUpdated,
                Boolean enabled, Boolean isDeleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.lastLogin = lastLogin;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
        this.enabled = enabled;
        this.isDeleted = isDeleted;
    }

    public User(String username, String email, UserRole role, LocalDateTime lastlogin,
                LocalDateTime createdDate, LocalDateTime lastUpdated,
                Boolean enabled, Boolean isDeleted) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.lastLogin = lastlogin;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
        this.enabled = enabled;
        this.isDeleted = isDeleted;
    }

    public User(String username, String email, String password, UserRole role,
                LocalDateTime createdDate, LocalDateTime lastUpdated,
                Boolean enabled, Boolean isDeleted) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
        this.enabled = enabled;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastlogin() {
        return lastLogin;
    }

    public void setLastlogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getDaysSinceLastLogin() {
        LocalDate currentDate = LocalDate.now();
        this.daysSinceLastLogin = ChronoUnit.DAYS.between(this.lastLogin, currentDate);
        return daysSinceLastLogin;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getStatus() {
        return this.enabled;
    }

    public void setStatus(Boolean enabled) {
        this.enabled = enabled;
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
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", lastLogin=" + lastLogin +
                ", daysSinceLastLogin=" + daysSinceLastLogin +
                ", createdDate=" + createdDate +
                ", lastUpdated=" + lastUpdated +
                ", enabled=" + enabled +
                ", isDeleted=" + isDeleted +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
