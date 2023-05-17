package com.medico.historiasclinicas.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetEmail(String email, String token) {
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setTo(email);
        passwordResetEmail.setSubject("Solicitud de restablecimiento de contraseña");
        passwordResetEmail.setText("Para restablecer tu contraseña, haz click en el siguiente enlace: "
                + "http://localhost:8080/reset-password?token=" + token);

        mailSender.send(passwordResetEmail);
    }

}
