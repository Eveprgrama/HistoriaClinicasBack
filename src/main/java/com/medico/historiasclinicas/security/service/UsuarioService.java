
package com.medico.historiasclinicas.security.service;

import com.medico.historiasclinicas.security.Entity.Usuario;
import com.medico.historiasclinicas.security.repository.iUsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional        
public class UsuarioService {
    @Autowired
    iUsuarioRepository iusuarioRepository;
    
     @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return iusuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return iusuarioRepository.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        iusuarioRepository.save(usuario);
    } 
    
     public Usuario getByEmail(String email) {
        return iusuarioRepository.findByEmail(email);
    }
     
     //Metodo para restablecer la contraseña
     public void changeUserPassword(Usuario user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        iusuarioRepository.save(user);
    }
}
