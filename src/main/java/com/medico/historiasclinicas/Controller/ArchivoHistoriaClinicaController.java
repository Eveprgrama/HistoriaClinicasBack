
package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Service.ArchivoHistoriaClinicaService;
import java.io.IOException;
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
@RequestMapping("historiasclinicas/archivos")
@CrossOrigin(origins = "http://localhost:4200")
public class ArchivoHistoriaClinicaController {

    private final ArchivoHistoriaClinicaService archivoHistoriaClinicaService;

    public ArchivoHistoriaClinicaController(ArchivoHistoriaClinicaService archivoHistoriaClinicaService) {
        this.archivoHistoriaClinicaService = archivoHistoriaClinicaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtenerArchivoHistoriaClinica(@PathVariable Long id) {
        ArchivoHistoriaClinica archivo = archivoHistoriaClinicaService.buscarArchivoHistoriaClinicaPorId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombre() + "\"");
        headers.setContentType(MediaType.parseMediaType(archivo.getTipo()));
        return new ResponseEntity<>(archivo.getArchivo(), headers, HttpStatus.OK);
    }

    @PostMapping("/nuevo/{historiaClinicaId}")
    public ResponseEntity<String> guardarArchivoHistoriaClinica(@RequestParam("file") MultipartFile file, @PathVariable Long historiaClinicaId) throws IOException {
        archivoHistoriaClinicaService.guardarArchivoHistoriaClinica(file, historiaClinicaId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Archivo subido correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArchivoHistoriaClinica(@PathVariable Long id) {
        archivoHistoriaClinicaService.eliminarArchivoHistoriaClinica(id);
        return ResponseEntity.status(HttpStatus.OK).body("Archivo eliminado correctamente.");
    }

}
