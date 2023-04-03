
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Service.HistoriaClinicaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("historiasclinicas")
public class HistoriaClinicaController {
    
    @Autowired
    private HistoriaClinicaService historiaClinicaService;
 
    @GetMapping("/{id}")
    public ResponseEntity<Optional<HistoriaClinica>> getHistoriaClinicaById(@PathVariable("id") Long id) {
        Optional<HistoriaClinica> historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(id);
        return ResponseEntity.ok().body(historiaClinica);
    }
 
    @PostMapping("/nueva")
    public ResponseEntity<HistoriaClinica> createHistoriaClinica(@RequestBody HistoriaClinica historiaClinica) {
        HistoriaClinica nuevaHistoriaClinica = historiaClinicaService.guardar(historiaClinica);
        return ResponseEntity.ok().body(nuevaHistoriaClinica);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinica> updateHistoriaClinica(@PathVariable("id") Long id, @RequestBody HistoriaClinica historiaClinica) {
        historiaClinica.setId(id);
        HistoriaClinica historiaClinicaActualizada = historiaClinicaService.updateHistoriaClinica(historiaClinica);
        return ResponseEntity.ok().body(historiaClinicaActualizada);
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
}
