/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.PacienteDTO;
import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Service.PacienteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{dni}")
    public ResponseEntity<PacienteDTO> buscarPorDni(@PathVariable String dni) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorDni(dni);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setNombre(paciente.getNombre());
            pacienteDTO.setApellido(paciente.getApellido());
            pacienteDTO.setDni(paciente.getDni());
            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> buscarPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido) {
        List<Paciente> pacientes = pacienteService.buscarPorNombreYApellido(nombre, apellido);
        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setNombre(paciente.getNombre());
            pacienteDTO.setApellido(paciente.getApellido());
            pacienteDTO.setDni(paciente.getDni());
            pacientesDTO.add(pacienteDTO);
        }
        return ResponseEntity.ok(pacientesDTO);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setDni(pacienteDTO.getDni());

        paciente = pacienteService.guardar(paciente);

        pacienteDTO.setId(paciente.getId());

        return ResponseEntity.ok(pacienteDTO);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable String dni) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorDni(dni);
        if (pacienteOptional.isPresent()) {
            pacienteService.eliminar(pacienteOptional.get());
            return ResponseEntity.ok("Paciente eliminado exitosamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
