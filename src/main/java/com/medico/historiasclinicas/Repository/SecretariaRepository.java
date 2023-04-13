
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Secretaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
    List<Secretaria> findByNombreAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
