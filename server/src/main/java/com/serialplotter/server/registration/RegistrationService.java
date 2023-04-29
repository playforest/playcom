package com.serialplotter.server.registration;

import com.serialplotter.server.registration.email.EmailSender;
import com.serialplotter.server.registration.token.ConfirmationToken;
import com.serialplotter.server.registration.token.ConfirmationTokenService;
import com.serialplotter.server.user.User;
import com.serialplotter.server.user.UserRole;
import com.serialplotter.server.user.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;


    @Transactional
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }


        Optional<User> existingUser = userService.findUserByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            Long userId = existingUser.get().getId();
            Optional<ConfirmationToken> existingToken = confirmationTokenService.getTokenByUserId(userId);

            if (existingToken.isPresent() && confirmationTokenService.getConfirmedAt(existingToken.get().getToken()) == null) {
                System.out.println("email found with unconfirmed token, removing user");
                userService.removeUser(userId);
                confirmationTokenService.deleteToken(userId);
            }
        }

        String token = userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        false, false)
        );

        System.out.println("token: " + token);


        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), buildEmail(request.getUsername(), link));
        return token;
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
    private String buildEmail(String recipientName, String activationLink) {
        String htmlTemplate = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Confirmation</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 6px 12px;\n" +
                "            color: #3498db;\n" +
                "            text-decoration: none;\n" +
                "            border: 1px solid #3498db;\n" +
                "            border-radius: 3px;\n" +
                "            font-size: 14px;\n" +
                "            font-family: 'Press Start 2P', cursive;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>Hi {{recipient}},</p>\n" +
                "    <p>Please click on the link below to activate your account:</p>\n" +
                "    <p>\n" +
                "        <a href=\"{{activation_link}}\" class=\"button\">Activate Now</a>\n" +
                "    </p>\n" +
                "    <p>Link will expire in 15 minutes.</p>\n" +
                "    <p>playcom</p>\n" +
                "</body>\n" +
                "</html>";

        return htmlTemplate
                .replace("{{recipient}}", recipientName)
                .replace("{{activation_link}}", activationLink);
    }
}
