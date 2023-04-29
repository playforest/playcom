package com.serialplotter.server.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
