
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ArchivoHistoriaClinicaRepository extends JpaRepository<ArchivoHistoriaClinica, Long> {
     List<ArchivoHistoriaClinica> findByHistoriaClinica(HistoriaClinica historiaClinica);

    public ArchivoHistoriaClinica save(MultipartFile archivoHistoriaClinica);
}
