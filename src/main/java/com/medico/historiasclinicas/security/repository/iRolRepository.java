
package com.medico.historiasclinicas.security.repository;

import com.medico.historiasclinicas.security.Entity.Rol;
import com.medico.historiasclinicas.security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

