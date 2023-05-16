
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Mapper.ActualizacionMapper;
import com.medico.historiasclinicas.Repository.ActualizacionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ActualizacionService {
    private final ActualizacionRepository actualizacionRepository;
    private final HistoriaClinicaService historiaClinicaService;
    private HistoriaClinica historiaClinica;
    private final ActualizacionMapper actualizacionMapper;
    
    @Autowired
    public ActualizacionService(ActualizacionRepository actualizacionRepository, HistoriaClinicaService historiaClinicaService, ActualizacionMapper actualizacionMapper) {
        this.actualizacionRepository = actualizacionRepository;
        this.historiaClinicaService = historiaClinicaService;
        this.actualizacionMapper = actualizacionMapper;
    }

    public ActualizacionDTO guardarActualizacion(ActualizacionDTO actualizacionDTO) {
        Actualizacion actualizacion = actualizacionMapper.toEntity(actualizacionDTO);
        Actualizacion savedActualizacion = actualizacionRepository.save(actualizacion);
        return actualizacionMapper.toDto(savedActualizacion);
    }

    public Actualizacion buscarActualizacionPorId(Long id) {
        return actualizacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Actualizacion no encontrada con ID: " + id));
    }

     public List<Actualizacion> buscarActualizacionesPorHistoriaClinicaOrdenadas(HistoriaClinica historiaClinica) {
        return actualizacionRepository.findByHistoriaClinicaOrderByFechaDesc(historiaClinica);
    }

    public void eliminarActualizacion(Long id) {
        actualizacionRepository.deleteById(id);
    }
    
    public void setHistoriaClinica(Optional<HistoriaClinica> historiaClinica) {
    this.historiaClinica = historiaClinica.orElse(null);
}
}
