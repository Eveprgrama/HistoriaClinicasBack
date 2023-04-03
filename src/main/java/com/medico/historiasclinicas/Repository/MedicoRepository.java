
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Medico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicoRepository extends JpaRepository<Medico, Long>{
    List<Medico> findByNombreAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
