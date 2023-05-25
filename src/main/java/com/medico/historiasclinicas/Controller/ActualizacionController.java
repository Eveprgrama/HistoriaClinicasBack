package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.DTO.ArchivoHistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.MedicacionDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Entity.Medicacion;
import com.medico.historiasclinicas.Service.ActualizacionService;
import com.medico.historiasclinicas.Service.HistoriaClinicaService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("actualizaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ActualizacionController {

    private final ActualizacionService actualizacionService;
    private final HistoriaClinicaService historiaClinicaService;

    @Autowired
    public ActualizacionController(ActualizacionService actualizacionService, HistoriaClinicaService historiaClinicaService) {
        this.actualizacionService = actualizacionService;
        this.historiaClinicaService = historiaClinicaService;
    }

    @PostMapping("/{historiaClinicaId}")
    public ResponseEntity<ActualizacionDTO> guardarActualizacion(@PathVariable Long historiaClinicaId, @RequestBody ActualizacionDTO actualizacionDTO) {
        actualizacionDTO.setHistoriaClinicaId(historiaClinicaId);
        ActualizacionDTO savedActualizacionDTO = actualizacionService.guardarActualizacion(actualizacionDTO);
        return ResponseEntity.ok(savedActualizacionDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_SECRETARIA', 'ROLE_ADMIN')")
    public ResponseEntity<ActualizacionDTO> buscarActualizacionPorId(@PathVariable Long id) {
        Actualizacion actualizacion = actualizacionService.buscarActualizacionPorId(id);

        ActualizacionDTO actualizacionDTO = new ActualizacionDTO();
        actualizacionDTO.setId(actualizacion.getId());
        actualizacionDTO.setFecha(actualizacion.getFecha());
        actualizacionDTO.setDescripcion(actualizacion.getDescripcion());
        actualizacionDTO.setIndicaciones(actualizacion.getIndicaciones());
        actualizacion.setMedicacion(actualizacionDTO.getMedicacion());
        actualizacionDTO.setDroga(actualizacion.getDroga());
        actualizacionDTO.setDosis(actualizacion.getDosis());
        actualizacionDTO.setPeso(actualizacion.getPeso());
        actualizacionDTO.setAltura(actualizacion.getAltura());

        return ResponseEntity.ok(actualizacionDTO);
    }
//Devuelve una lista de las actualizaciones de la historia clinica

    @GetMapping("/historiaClinica/{historiaClinicaId}")
    public ResponseEntity<HistoriaClinicaDTO> buscarActualizacionesPorHistoriaClinicaOrdenadas(@PathVariable Long historiaClinicaId) {
        HistoriaClinica historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(historiaClinicaId)
                .orElseThrow(() -> new NoSuchElementException("Historia Clínica no encontrada con ID: " + historiaClinicaId));

        List<Actualizacion> actualizaciones = actualizacionService.buscarActualizacionesPorHistoriaClinicaOrdenadas(historiaClinica);

        HistoriaClinicaDTO historiaClinicaDTO = new HistoriaClinicaDTO();
        historiaClinicaDTO.setPacienteId(historiaClinica.getPaciente().getId()); // Suponiendo que tienes una relación con Paciente
        historiaClinicaDTO.setFechaCreacion(historiaClinica.getFechaCreacion());
        historiaClinicaDTO.setEnfermedad(historiaClinica.getEnfermedad());
        historiaClinicaDTO.setDescripcion(historiaClinica.getDescripcion());
        historiaClinicaDTO.setPeso(historiaClinica.getPeso());
        historiaClinicaDTO.setAltura(historiaClinica.getAltura());
        historiaClinicaDTO.setIndicaciones(historiaClinica.getIndicaciones());
        // Suponiendo que tienes un método para convertir la lista de archivos a DTOs
        List<ArchivoHistoriaClinicaDTO> archivosDTO = new ArrayList<>();
        for (ArchivoHistoriaClinica archivo : historiaClinica.getArchivos()) {
            archivosDTO.add(new ArchivoHistoriaClinicaDTO(archivo));
        }
        historiaClinicaDTO.setArchivos(archivosDTO);
        
        List<MedicacionDTO> medicacionDTO = new ArrayList<>();
        for (Medicacion medicacion : historiaClinica.getMedicacion()){
            medicacionDTO.add(new MedicacionDTO(medicacion));
        }
        historiaClinicaDTO.setMedicacion(medicacionDTO);

        List<ActualizacionDTO> actualizacionesDTO = new ArrayList<>();
        for (Actualizacion actualizacion : actualizaciones) {
            ActualizacionDTO actualizacionDTO = new ActualizacionDTO();
            actualizacionDTO.setId(actualizacion.getId());
            actualizacionDTO.setFecha(actualizacion.getFecha());
            actualizacionDTO.setDescripcion(actualizacion.getDescripcion());
            actualizacionDTO.setIndicaciones(actualizacion.getIndicaciones());
            actualizacion.setMedicacion(actualizacionDTO.getMedicacion());
            actualizacionDTO.setDroga(actualizacion.getDroga());
            actualizacionDTO.setDosis(actualizacion.getDosis());
            actualizacionDTO.setPeso(actualizacion.getPeso());
            actualizacionDTO.setAltura(actualizacion.getAltura());

            actualizacionesDTO.add(actualizacionDTO);
        }

        historiaClinicaDTO.setActualizaciones(actualizacionesDTO);

        return ResponseEntity.ok(historiaClinicaDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_ADMIN')")
    public ResponseEntity<String> eliminarActualizacion(@PathVariable Long id) {
        actualizacionService.eliminarActualizacion(id);

        return ResponseEntity.ok("Actualización eliminada exitosamente.");
    }
}
