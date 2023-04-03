package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Repository.ActualizacionRepository;
import com.medico.historiasclinicas.Repository.HistoriaClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private ActualizacionRepository actualizacionRepository;

    public Optional<HistoriaClinica> buscarHistoriaClinicabyId(Long Id) {
        return historiaClinicaRepository.findById(Id);
    }

    public Optional<HistoriaClinica> buscarPorDniPaciente(String dni) {
        return historiaClinicaRepository.findByPaciente_Dni(dni);
    }

    public List<HistoriaClinica> buscarPorNombrePaciente(String nombre, String apellido) {
        return historiaClinicaRepository.findByNombreAndApellidoContainingIgnoreCase(nombre, apellido);
    }

    public HistoriaClinica guardar(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    public void actualizarHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO) {
        // Obtener la historia clínica actual de la base de datos a través del id proporcionado en el DTO.
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaClinicaDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("La historia clínica no existe"));

        // Actualizar los campos de la historia clínica con los valores del DTO.
        historiaClinica.setEnfermedad(historiaClinicaDTO.getEnfermedad());
        historiaClinica.setDescripcion(historiaClinicaDTO.getDescripcion());
        historiaClinica.setMedicacion(historiaClinicaDTO.getMedicacion());
        historiaClinica.setPeso(historiaClinicaDTO.getPeso());
        historiaClinica.setAltura(historiaClinicaDTO.getAltura());
        historiaClinica.setIndicaciones(historiaClinicaDTO.getIndicaciones());

        // Guardar la historia clínica actualizada en la base de datos.
        historiaClinicaRepository.save(historiaClinica);
    }

    public void eliminar(Long id) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la historia clínica con el id: " + id));
        historiaClinicaRepository.delete(historiaClinica);
    }

    public void agregarActualizacion(Long historiaClinicaId, Actualizacion actualizacion) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaClinicaId)
                .orElseThrow(() -> new EntityNotFoundException("Historia clínica no encontrada"));

        historiaClinica.getActualizaciones().add(actualizacion);
        actualizacion.setHistoriaClinica(historiaClinica);
        actualizacionRepository.save(actualizacion);
        historiaClinicaRepository.save(historiaClinica);
    }

    public List<Actualizacion> buscarActualizacionesPorHistoriaClinica(HistoriaClinica historiaClinica) {
        return actualizacionRepository.findByHistoriaClinicaOrderByFechaDesc(historiaClinica);
    }

    public HistoriaClinica updateHistoriaClinica(HistoriaClinica historiaClinica) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
