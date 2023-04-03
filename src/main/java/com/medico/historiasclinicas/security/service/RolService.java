
package com.medico.historiasclinicas.security.service;

import com.medico.historiasclinicas.security.Entity.Rol;
import com.medico.historiasclinicas.security.Enums.RolNombre;
import com.medico.historiasclinicas.security.repository.iRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
