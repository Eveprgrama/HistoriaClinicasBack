package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Mapper.ActualizacionMapper;
import com.medico.historiasclinicas.Mapper.ArchivoHistoriaClinicaMapper;
import com.medico.historiasclinicas.Mapper.HistoriaClinicaMapper;
import com.medico.historiasclinicas.Repository.ActualizacionRepository;
import com.medico.historiasclinicas.Repository.HistoriaClinicaRepository;
import com.medico.historiasclinicas.Repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    private ActualizacionRepository actualizacionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ActualizacionMapper actualizacionMapper;

    @Autowired
    private ArchivoHistoriaClinicaMapper archivoHistoriaClinicaMapper;

    @Autowired
    private HistoriaClinicaMapper historiaClinicaMapper;

    public Optional<HistoriaClinica> buscarHistoriaClinicabyId(Long Id) {
        return historiaClinicaRepository.findById(Id);
    }

    public Optional<HistoriaClinica> buscarPorDniPaciente(String dni) {
        return historiaClinicaRepository.findByPaciente_Dni(dni);
    }

    public List<HistoriaClinica> buscarPorNombrePaciente(String nombre, String apellido) {
        return historiaClinicaRepository.findByPacienteNombreAndPacienteApellidoContainingIgnoreCase(nombre, apellido);
    }

    public void actualizarHistoriaClinica(HistoriaClinicaDTO historiaClinicaDTO) {
        // Obtener la historia clínica actual de la base de datos a través del id proporcionado en el DTO.
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(historiaClinicaDTO.getPacienteId())
                .orElseThrow(() -> new NoSuchElementException("La historia clínica no existe"));

        // Actualizar los campos de la historia clínica con los valores del DTO.
        historiaClinica.setEnfermedad(historiaClinicaDTO.getEnfermedad());
        historiaClinica.setDescripcion(historiaClinicaDTO.getDescripcion());
        historiaClinica.setMedicacion(historiaClinicaDTO.getMedicacion());
        historiaClinica.setDroga(historiaClinicaDTO.getDroga());
        historiaClinica.setDósis(historiaClinicaDTO.getDosis());
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

    public HistoriaClinica save(HistoriaClinicaDTO hcDTO, Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id " + idPaciente));

        HistoriaClinica hc = historiaClinicaMapper.toEntity(hcDTO);
        hc.setPaciente(paciente);

        List<Actualizacion> actualizaciones = hcDTO.getActualizaciones().stream()
                .map(actualizacionMapper::toEntity)
                .collect(Collectors.toList());
        hc.setActualizaciones(actualizaciones);

        List<ArchivoHistoriaClinica> archivos = hcDTO.getArchivos().stream()
                .map(archivoHistoriaClinicaMapper::toEntity)
                .collect(Collectors.toList());
        hc.setArchivos(archivos);
        actualizaciones.forEach(actualizacion -> actualizacion.setHistoriaClinica(hc));
        archivos.forEach(archivo -> archivo.setHistoriaClinica(hc));

        entityManager.persist(hc);
        return hc;
    }

}
