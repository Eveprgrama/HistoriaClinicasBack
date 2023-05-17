package com.medico.historiasclinicas.security.service;

import com.medico.historiasclinicas.security.Entity.PasswordResetToken;
import com.medico.historiasclinicas.security.Entity.Usuario;
import com.medico.historiasclinicas.security.repository.PasswordResetTokenRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public void savePasswordResetTokenForUser(Usuario user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        //Esta parte establece un tiempo de expiración
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24); // agrega 24 horas a la hora actual

        tokenRepository.save(myToken);
    }

    public Usuario validatePasswordResetToken(String token) {
        PasswordResetToken passToken = tokenRepository.findByToken(token);
        if (passToken == null || passToken.isExpired()) {
            return null;
        } else {
            return passToken.getUser();
        }
    }

    public void invalidatePasswordResetToken(String token) {
        PasswordResetToken passToken = tokenRepository.findByToken(token);
        if (passToken != null) {
            tokenRepository.delete(passToken);
        }
    }

}
