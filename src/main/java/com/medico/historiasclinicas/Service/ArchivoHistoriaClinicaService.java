
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import com.medico.historiasclinicas.Repository.ArchivoHistoriaClinicaRepository;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoHistoriaClinicaService {
       private final ArchivoHistoriaClinicaRepository archivoHistoriaClinicaRepository;
       private final HistoriaClinicaService historiaClinicaService;

    @Autowired
    public ArchivoHistoriaClinicaService(ArchivoHistoriaClinicaRepository archivoHistoriaClinicaRepository, HistoriaClinicaService historiaClinicaService) {
        this.archivoHistoriaClinicaRepository = archivoHistoriaClinicaRepository;
        this.historiaClinicaService = historiaClinicaService;
    }
    
    public ArchivoHistoriaClinica guardarArchivoHistoriaClinica(ArchivoHistoriaClinica archivoHistoriaClinica) {
        return archivoHistoriaClinicaRepository.save(archivoHistoriaClinica);
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

    ArchivoHistoriaClinica archivoHistoriaClinica = new ArchivoHistoriaClinica();
    archivoHistoriaClinica.setNombre(archivo.getOriginalFilename());
    archivoHistoriaClinica.setTipo(archivo.getContentType());
    archivoHistoriaClinica.setArchivo(archivo.getBytes());
    archivoHistoriaClinica.setHistoriaClinica(historiaClinica.get());

    return archivoHistoriaClinicaRepository.save(archivoHistoriaClinica);
}


}
