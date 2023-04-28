package com.serialplotter.server.registration;

import com.serialplotter.server.registration.token.ConfirmationToken;
import com.serialplotter.server.registration.token.ConfirmationTokenService;
import com.serialplotter.server.user.User;
import com.serialplotter.server.user.UserRole;
import com.serialplotter.server.user.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

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
                        false, false)
                );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);

        userService.enableUser(confirmationToken.getUser().getId());

        return "confirmed";
    }
}
