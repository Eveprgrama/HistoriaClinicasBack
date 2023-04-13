package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    Optional<HistoriaClinica> findByPaciente_Dni(String dni);
  
    public List<HistoriaClinica> findByPacienteNombreAndPacienteApellidoContainingIgnoreCase(String nombre, String apellido);

}
