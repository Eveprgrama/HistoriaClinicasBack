package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.DTO.PacienteDTO;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Mapper.ActualizacionMapper;
import com.medico.historiasclinicas.Mapper.ArchivoHistoriaClinicaMapper;
import com.medico.historiasclinicas.Mapper.HistoriaClinicaMapper;
import com.medico.historiasclinicas.Service.HistoriaClinicaService;
import com.medico.historiasclinicas.Service.PacienteService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("historiasclinicas")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoriaClinicaController {

    @Autowired
    private final HistoriaClinicaService historiaClinicaService;
    private final PacienteService pacienteService;
    private final HistoriaClinicaMapper historiaClinicaMapper;
    private final ActualizacionMapper actualizacionMapper;
    private final ArchivoHistoriaClinicaMapper archivoHistoriaClinicaMapper;

    public HistoriaClinicaController(ActualizacionMapper actualizacionMapper, ArchivoHistoriaClinicaMapper archivoHistoriaClinicaMapper, HistoriaClinicaService historiaClinicaService, HistoriaClinicaMapper historiaClinicaMapper, PacienteService pacienteService) {
        this.actualizacionMapper = actualizacionMapper;
        this.archivoHistoriaClinicaMapper = archivoHistoriaClinicaMapper;
        this.historiaClinicaService = historiaClinicaService;
        this.historiaClinicaMapper = historiaClinicaMapper;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HistoriaClinica>> getHistoriaClinicaById(@PathVariable("id") Long id) {
        Optional<HistoriaClinica> historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(id);
        return ResponseEntity.ok().body(historiaClinica);
    }

    @PostMapping("/nuevas/{idPaciente}")
    public ResponseEntity<HistoriaClinicaDTO> createHistoriaClinica(@RequestBody HistoriaClinicaDTO historiaClinicaDTO, @PathVariable("idPaciente") Long pacienteId) {
        // Verificar si el paciente existe
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(pacienteId);
        if (!pacienteOptional.isPresent()) {
            throw new RuntimeException("No se encontró un paciente con el ID proporcionado.");
        }

        HistoriaClinica nuevaHistoriaClinica = historiaClinicaService.save(historiaClinicaDTO, pacienteId);

        HistoriaClinicaDTO newHistoriaClinicaDTO = historiaClinicaMapper.toDto(nuevaHistoriaClinica);

        return ResponseEntity.ok(newHistoriaClinicaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaClinica(@PathVariable("id") Long id) {
        historiaClinicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{dni}")
    public ResponseEntity<Optional<HistoriaClinica>> getHistoriasClinicasByDni(@PathVariable("dni") String dni) {
        Optional<HistoriaClinica> historiasClinicas = historiaClinicaService.buscarPorDniPaciente(dni);
        return ResponseEntity.ok().body(historiasClinicas);
    }

    @GetMapping("/paciente")
    public ResponseEntity<List<HistoriaClinica>> getHistoriasClinicasByNombreYApellido(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido) {
        List<HistoriaClinica> historiasClinicas = historiaClinicaService.buscarPorNombrePaciente(nombre, apellido);
        return ResponseEntity.ok().body(historiasClinicas);
    }

    @PostMapping("/{id}/actualizaciones")
    public ResponseEntity<String> actualizarHistoriaClinica(@RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
        historiaClinicaService.actualizarHistoriaClinica(historiaClinicaDTO);
        return ResponseEntity.ok("Historia Clínica actualizada exitosamente.");
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable("id") Long id) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(id);
        if (optPaciente.isPresent()) {
            Paciente paciente = optPaciente.get();
            PacienteDTO pacienteDTO = new PacienteDTO();

            pacienteDTO.setId(paciente.getId());
            pacienteDTO.setNombre(paciente.getNombre());
            pacienteDTO.setApellido(paciente.getApellido());
            pacienteDTO.setDni(paciente.getDni());
            pacienteDTO.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteDTO.setDireccion(paciente.getDireccion());
            pacienteDTO.setTelefono(paciente.getTelefono());
            pacienteDTO.setEmail(paciente.getEmail());

            boolean existeHistoriaClinica = historiaClinicaService.existeHistoriaClinicaPorPacienteId(id);
            pacienteDTO.setHistoriasClinicas(existeHistoriaClinica ? Collections.singletonList(new HistoriaClinicaDTO()) : Collections.emptyList());

            return ResponseEntity.ok().body(pacienteDTO);
        }
        return ResponseEntity.notFound().build();
    }

}
