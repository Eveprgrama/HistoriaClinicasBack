package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.HistoriaClinica;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    Optional<HistoriaClinica> findByPaciente_Dni(String dni);

    public List<HistoriaClinica> findByPacienteNombreAndPacienteApellidoContainingIgnoreCase(String nombre, String apellido);

    HistoriaClinica findByPacienteId(Long pacienteId);

    @Transactional
    @Modifying
    @Query("DELETE FROM HistoriaClinica hc WHERE hc.id = :id AND hc.paciente.id = :idPaciente")
    void deleteByIdAndPacienteId(@Param("id") Long id, @Param("idPaciente") Long idPaciente);

}
