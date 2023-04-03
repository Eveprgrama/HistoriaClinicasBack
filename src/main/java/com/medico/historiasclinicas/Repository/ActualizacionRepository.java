
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Actualizacion;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualizacionRepository extends JpaRepository<Actualizacion, Long> {
    List<Actualizacion> findByHistoriaClinicaOrderByFechaDesc(HistoriaClinica historiaClinica);
}
