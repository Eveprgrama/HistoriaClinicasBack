
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.SecretariaDTO;
import com.medico.historiasclinicas.Entity.Secretaria;
import com.medico.historiasclinicas.Service.SecretariaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secretarias")
public class SecretariaController {

    private final SecretariaService secretariaService;

    @Autowired
    public SecretariaController(SecretariaService secretariaService) {
        this.secretariaService = secretariaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretariaDTO> buscarPorId(@PathVariable Long id) {
        Secretaria secretaria = secretariaService.buscarPorId(id);

        if (secretaria == null) {
            return ResponseEntity.notFound().build();
        }

        SecretariaDTO secretariaDTO = new SecretariaDTO();
        secretariaDTO.setId(secretaria.getId());
        secretariaDTO.setNombre(secretaria.getNombre());
        secretariaDTO.setApellido(secretaria.getApellido());

        return ResponseEntity.ok(secretariaDTO);
    }

    @GetMapping
    public ResponseEntity<List<SecretariaDTO>> buscarTodos() {
        List<Secretaria> secretarias = secretariaService.buscarTodos();

        List<SecretariaDTO> secretariasDTO = new ArrayList<>();
        for (Secretaria secretaria : secretarias) {
            SecretariaDTO secretariaDTO = new SecretariaDTO();
            secretariaDTO.setId(secretaria.getId());
            secretariaDTO.setNombre(secretaria.getNombre());
            secretariaDTO.setApellido(secretaria.getApellido());
            
            secretariasDTO.add(secretariaDTO);
        }

        return ResponseEntity.ok(secretariasDTO);
    }

    @PostMapping
    public ResponseEntity<SecretariaDTO> guardar(@RequestBody SecretariaDTO secretariaDTO) {
        Secretaria secretaria = new Secretaria();
        secretaria.setNombre(secretariaDTO.getNombre());
        secretaria.setApellido(secretariaDTO.getApellido());

        secretaria = secretariaService.guardar(secretaria);

        secretariaDTO.setId(secretaria.getId());

        return ResponseEntity.ok(secretariaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        Secretaria secretaria = secretariaService.buscarPorId(id);

        if (secretaria == null) {
            return ResponseEntity.notFound().build();
        }

        secretariaService.eliminar(secretaria);

        return ResponseEntity.ok("Secretaria eliminada exitosamente.");
    }
}

