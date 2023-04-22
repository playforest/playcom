package com.serialplotter.server.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.role = :role, " +
            "u.lastLogin = :lastLogin, u.lastUpdated=:lastUpdated, u.status = :status WHERE u.id = :id")
    void updateUserById(@Param("id") Long id, @Param("name") String name,
                       @Param("email") String email, @Param("role") String role,
                       @Param("lastLogin") LocalDate lastLogin, @Param("lastUpdated") LocalDateTime lastUpdated,
                       @Param("status") Boolean status);

}
