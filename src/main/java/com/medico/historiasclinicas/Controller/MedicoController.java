
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.MedicoDTO;
import com.medico.historiasclinicas.Entity.Medico;
import com.medico.historiasclinicas.Service.MedicoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicoController {
    @Autowired
private MedicoService medicoService;

@GetMapping("/{id}")
public ResponseEntity<MedicoDTO> buscarMedicoPorId(@PathVariable Long id) {
    Medico medico = medicoService.buscarPorId(id);

    if (medico == null) {
        return ResponseEntity.notFound().build();
    }

    MedicoDTO medicoDTO = new MedicoDTO();
    medicoDTO.setId(medico.getId());
    medicoDTO.setNombre(medico.getNombre());
    medicoDTO.setApellido(medico.getApellido());
    medicoDTO.setMatricula(medico.getMatricula());

    return ResponseEntity.ok(medicoDTO);
}

@GetMapping("/")
public ResponseEntity<List<MedicoDTO>> buscarTodos() {
    List<Medico> medicos = medicoService.buscarTodos();

    List<MedicoDTO> medicosDTO = new ArrayList<>();
    for (Medico medico : medicos) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNombre(medico.getNombre());
        medicoDTO.setApellido(medico.getApellido());
        medicoDTO.setMatricula(medico.getMatricula());

        medicosDTO.add(medicoDTO);
    }

    return ResponseEntity.ok(medicosDTO);
}

@PostMapping("/")
public ResponseEntity<MedicoDTO> guardarMedico(@RequestBody MedicoDTO medicoDTO) {
    Medico medico = new Medico();
    medico.setNombre(medicoDTO.getNombre());
    medico.setApellido(medicoDTO.getApellido());
    medico.setMatricula(medicoDTO.getMatricula());

    medico = medicoService.guardar(medico);

    medicoDTO.setId(medico.getId());

    return ResponseEntity.ok(medicoDTO);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> eliminarMedico(@PathVariable Long id) {
    Medico medico = medicoService.buscarPorId(id);

    if (medico == null) {
        return ResponseEntity.notFound().build();
    }

    medicoService.eliminar(medico);

    return ResponseEntity.ok("Medico eliminado exitosamente.");
}

}
