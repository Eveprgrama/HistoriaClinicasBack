
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Repository.ActualizacionRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizacionService {
    private final ActualizacionRepository actualizacionRepository;
    private final HistoriaClinicaService historiaClinicaService;
    private HistoriaClinica historiaClinica;
    
    @Autowired
    public ActualizacionService(ActualizacionRepository actualizacionRepository, HistoriaClinicaService historiaClinicaService) {
        this.actualizacionRepository = actualizacionRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    public Actualizacion guardarActualizacion(Actualizacion actualizacion) {
        return actualizacionRepository.save(actualizacion);
    }

    public Actualizacion buscarActualizacionPorId(Long id) {
        return actualizacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Actualizacion no encontrada con ID: " + id));
    }

    public List<Actualizacion> buscarActualizacionesPorHistoriaClinica(HistoriaClinica historiaClinica) {
        return actualizacionRepository.findByHistoriaClinicaOrderByFechaDesc(historiaClinica);
    }

    public void eliminarActualizacion(Long id) {
        actualizacionRepository.deleteById(id);
    }
    
    public void setHistoriaClinica(Optional<HistoriaClinica> historiaClinica) {
    this.historiaClinica = historiaClinica.orElse(null);
}
}
