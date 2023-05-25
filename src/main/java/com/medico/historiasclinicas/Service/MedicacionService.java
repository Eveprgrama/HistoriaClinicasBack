
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.Medicacion;
import com.medico.historiasclinicas.Repository.MedicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicacionService {
    private final MedicacionRepository medicacionRepository;

    @Autowired
    public MedicacionService(MedicacionRepository medicacionRepository) {
        this.medicacionRepository = medicacionRepository;
    }

    public Medicacion save(Medicacion medicacion) {
        return medicacionRepository.save(medicacion);
    }
}

