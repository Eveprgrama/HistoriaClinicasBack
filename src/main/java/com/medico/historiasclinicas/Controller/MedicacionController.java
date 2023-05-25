package com.medico.historiasclinicas.Controller;

import com.medico.historiasclinicas.DTO.MedicacionDTO;
import com.medico.historiasclinicas.Entity.Medicacion;
import com.medico.historiasclinicas.Mapper.MedicacionMapper;
import com.medico.historiasclinicas.Service.MedicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicacion")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicacionController {

    private final MedicacionService medicacionService;
    private final MedicacionMapper medicacionMapper;

    @Autowired
    public MedicacionController(MedicacionService medicacionService, MedicacionMapper medicacionMapper) {
        this.medicacionService = medicacionService;
        this.medicacionMapper = medicacionMapper;
    }

    @PostMapping("/nueva/{id}")
    public ResponseEntity<MedicacionDTO> createMedicacion(@RequestBody MedicacionDTO medicacionDTO) {
        Medicacion medicacion = medicacionMapper.toEntity(medicacionDTO);
        medicacion = medicacionService.save(medicacion);
        return ResponseEntity.ok(medicacionMapper.toDto(medicacion));
    }
}
