package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
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
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_ADMIN')")
    public ResponseEntity<ActualizacionDTO> guardarActualizacion(@PathVariable Long historiaClinicaId, @RequestBody ActualizacionDTO actualizacionDTO) {
        HistoriaClinica historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(historiaClinicaId)
                .orElseThrow(() -> new NoSuchElementException("Historia Clínica no encontrada con ID: " + historiaClinicaId));

        Actualizacion actualizacion = new Actualizacion();
        actualizacion.setFecha(actualizacionDTO.getFecha());
        actualizacion.setDescripcion(actualizacionDTO.getDescripcion());
        actualizacion.setHistoriaClinica(historiaClinica);

        actualizacion = actualizacionService.guardarActualizacion(actualizacion);

        actualizacionDTO.setId(actualizacion.getId());

        return ResponseEntity.ok(actualizacionDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_SECRETARIA', 'ROLE_ADMIN')")
    public ResponseEntity<ActualizacionDTO> buscarActualizacionPorId(@PathVariable Long id) {
        Actualizacion actualizacion = actualizacionService.buscarActualizacionPorId(id);

        ActualizacionDTO actualizacionDTO = new ActualizacionDTO();
        actualizacionDTO.setId(actualizacion.getId());
        actualizacionDTO.setFecha(actualizacion.getFecha());
        actualizacionDTO.setDescripcion(actualizacion.getDescripcion());

        return ResponseEntity.ok(actualizacionDTO);
    }
//Devuelve una lista de las actualizaciones de la historia clinica

    @GetMapping("/historiaClinica/{historiaClinicaId}")
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_SECRETARIA', 'ROLE_ADMIN')")
    public ResponseEntity<List<ActualizacionDTO>> buscarActualizacionesPorHistoriaClinica(@PathVariable Long historiaClinicaId) {
        HistoriaClinica historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(historiaClinicaId)
                .orElseThrow(() -> new NoSuchElementException("Historia Clínica no encontrada con ID: " + historiaClinicaId));

        List<Actualizacion> actualizaciones = actualizacionService.buscarActualizacionesPorHistoriaClinica(historiaClinica);

        List<ActualizacionDTO> actualizacionesDTO = new ArrayList<>();
        for (Actualizacion actualizacion : actualizaciones) {
            ActualizacionDTO actualizacionDTO = new ActualizacionDTO();
            actualizacionDTO.setId(actualizacion.getId());
            actualizacionDTO.setFecha(actualizacion.getFecha());
            actualizacionDTO.setDescripcion(actualizacion.getDescripcion());

            actualizacionesDTO.add(actualizacionDTO);
        }

        return ResponseEntity.ok(actualizacionesDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEDICO', 'ROLE_ADMIN')")
    public ResponseEntity<String> eliminarActualizacion(@PathVariable Long id) {
        actualizacionService.eliminarActualizacion(id);

        return ResponseEntity.ok("Actualización eliminada exitosamente.");
    }
}
