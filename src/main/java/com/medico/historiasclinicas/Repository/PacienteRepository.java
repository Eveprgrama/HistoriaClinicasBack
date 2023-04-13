
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Paciente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(String dni);
    //esto ignora mayusculas y minusculas al buscar paciene por nombre y apellido
    List<Paciente> findByNombreAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
