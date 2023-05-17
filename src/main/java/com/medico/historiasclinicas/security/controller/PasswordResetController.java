package com.medico.historiasclinicas.security.controller;

import com.medico.historiasclinicas.security.Entity.PasswordResetToken;
import com.medico.historiasclinicas.security.Entity.Usuario;
import com.medico.historiasclinicas.security.repository.PasswordResetTokenRepository;
import com.medico.historiasclinicas.security.repository.iUsuarioRepository;
import com.medico.historiasclinicas.security.service.EmailService;
import com.medico.historiasclinicas.security.service.PasswordResetService;
import com.medico.historiasclinicas.security.service.UsuarioService;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PasswordResetController {

    @Autowired
    private iUsuarioRepository userRepository;

    @Autowired
    private PasswordResetService tokenService;
    
     @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/forgot-password")
public ResponseEntity<?> forgotPassword(@RequestBody Map<String, Object> requestParams) {
    String userEmail = (String) requestParams.get("email");
    // Busca el usuario en la base de datos utilizando el correo electrónico
    Usuario user = usuarioService.getByEmail(userEmail);
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado");
    }
    // Genera un token y guárdalo en la base de datos o un servicio de caché con una fecha de vencimiento
    String token = UUID.randomUUID().toString();
    // Guarda el token en la base de datos o en el servicio de caché
    tokenService.savePasswordResetTokenForUser(user, token);
    // Envía un correo electrónico al usuario con el enlace de restablecimiento de contraseña (que incluye el token)
    emailService.sendResetEmail(user.getEmail(), token);
    return ResponseEntity.ok("Se ha enviado un correo electrónico de restablecimiento de contraseña");
}

    
 @PostMapping("/reset-password")
public ResponseEntity<?> handlePasswordReset(@RequestBody Map<String, Object> requestParams) {
    String token = (String) requestParams.get("token");
    String newPassword = (String) requestParams.get("newPassword");
    
    Usuario user = tokenService.validatePasswordResetToken(token);
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token inválido o expirado");
    }

    usuarioService.changeUserPassword(user, newPassword);
    
    // Invalida el token después de su uso para que no pueda ser reutilizado
    tokenService.invalidatePasswordResetToken(token);
    
    return ResponseEntity.ok("La contraseña ha sido cambiada exitosamente");
}

    

}
