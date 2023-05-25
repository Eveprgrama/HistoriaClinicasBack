
package com.medico.historiasclinicas.Repository;

import com.medico.historiasclinicas.Entity.Medicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicacionRepository extends JpaRepository<Medicacion, Long> {
    
}
