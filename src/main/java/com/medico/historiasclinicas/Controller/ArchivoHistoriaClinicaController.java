
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Service.ArchivoHistoriaClinicaService;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/archivos")
@CrossOrigin(origins = "http://localhost:4200")
public class ArchivoHistoriaClinicaController {
    private final ArchivoHistoriaClinicaService archivoHistoriaClinicaService;

    @Autowired
    public ArchivoHistoriaClinicaController(ArchivoHistoriaClinicaService archivoHistoriaClinicaService) {
        this.archivoHistoriaClinicaService = archivoHistoriaClinicaService;
    }

    @PostMapping("/{historiaClinicaId}")
    public ResponseEntity<?> subirArchivo(@RequestParam("archivo") MultipartFile archivo,
                                          @PathVariable("historiaClinicaId") Long historiaClinicaId) {
        try {
            ArchivoHistoriaClinica archivoGuardado = archivoHistoriaClinicaService.guardarArchivoHistoriaClinica(archivo, historiaClinicaId);
            return ResponseEntity.ok(archivoGuardado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{archivoId}")
    public ResponseEntity<?> descargarArchivo(@PathVariable("archivoId") Long archivoId) {
        try {
            ArchivoHistoriaClinica archivo = archivoHistoriaClinicaService.buscarArchivoHistoriaClinicaPorId(archivoId);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombre() + "\"");
            headers.setContentType(MediaType.parseMediaType(archivo.getTipo()));
            return new ResponseEntity<>(archivo.getBytes(), headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al descargar el archivo");
        }
    }

    @DeleteMapping("/{archivoId}")
    public ResponseEntity<?> eliminarArchivo(@PathVariable("archivoId") Long archivoId) {
        try {
            archivoHistoriaClinicaService.eliminarArchivoHistoriaClinica(archivoId);
            return ResponseEntity.ok("Archivo eliminado correctamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

