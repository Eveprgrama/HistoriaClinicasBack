
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.DTO.ArchivoHistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Repository.ArchivoHistoriaClinicaRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ArchivoHistoriaClinicaService {
       private final ArchivoHistoriaClinicaRepository archivoHistoriaClinicaRepository;
       private final HistoriaClinicaService historiaClinicaService;

    @Autowired
    public ArchivoHistoriaClinicaService(ArchivoHistoriaClinicaRepository archivoHistoriaClinicaRepository, HistoriaClinicaService historiaClinicaService) {
        this.archivoHistoriaClinicaRepository = archivoHistoriaClinicaRepository;
        this.historiaClinicaService = historiaClinicaService;
    }
    
    
    private List<ArchivoHistoriaClinicaDTO> convertirArchivosA_DTOs(List<ArchivoHistoriaClinica> archivos) {
    List<ArchivoHistoriaClinicaDTO> archivosDTO = new ArrayList<>();
    for (ArchivoHistoriaClinica archivo : archivos) {
        archivosDTO.add(new ArchivoHistoriaClinicaDTO(archivo));
    }
    return archivosDTO;
}


    public ArchivoHistoriaClinica buscarArchivoHistoriaClinicaPorId(Long id) {
        return archivoHistoriaClinicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ArchivoHistoriaClinica no encontrada con ID: " + id));
    }

    public List<ArchivoHistoriaClinica> buscarArchivosPorHistoriaClinica(HistoriaClinica historiaClinica) {
        return archivoHistoriaClinicaRepository.findByHistoriaClinica(historiaClinica);
    }

    public void eliminarArchivoHistoriaClinica(Long id) {
        archivoHistoriaClinicaRepository.deleteById(id);
    }
 public ArchivoHistoriaClinica guardarArchivoHistoriaClinica(MultipartFile archivo, Long historiaClinicaId) throws IOException {
    Optional<HistoriaClinica> historiaClinica = historiaClinicaService.buscarHistoriaClinicabyId(historiaClinicaId);
    if (!historiaClinica.isPresent()) {
        throw new NoSuchElementException("Historia Clínica no encontrada con ID: " + historiaClinicaId);
    }

    // Almacena el archivo en el sistema de archivos local
    String nombreArchivo = StringUtils.cleanPath(archivo.getOriginalFilename());
    Path rutaArchivo = Paths.get("ruta/a/tu/carpeta/de/archivos/" + nombreArchivo);
    Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

    // Genera la URL para acceder al archivo
    String url = "http://localhost:8080/archivos/" + nombreArchivo;

    ArchivoHistoriaClinica archivoHistoriaClinica = new ArchivoHistoriaClinica();
    archivoHistoriaClinica.setNombre(archivo.getOriginalFilename());
    archivoHistoriaClinica.setTipo(archivo.getContentType());
    archivoHistoriaClinica.setUrl(url);
    archivoHistoriaClinica.setHistoriaClinica(historiaClinica.get());

    return archivoHistoriaClinicaRepository.save(archivoHistoriaClinica);
}

}
