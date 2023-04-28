package com.serialplotter.server.registration;

import com.serialplotter.server.user.User;
import com.serialplotter.server.user.UserRole;
import com.serialplotter.server.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        true, false)
                );
    }
}
