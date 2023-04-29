package com.serialplotter.server.registration.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public LocalDateTime getConfirmedAt(String token) {
        return confirmationTokenRepository.findByToken(token).get().getConfirmedAt();
    }

    public Optional<ConfirmationToken> getTokenByUserId(Long userId) {
        return confirmationTokenRepository.findByUserId(userId);
    }

    public void deleteToken(Long userId) {
        confirmationTokenRepository.deleteTokenByUserId(userId);
    }
}
