
package com.medico.historiasclinicas.Mapper;

import com.medico.historiasclinicas.DTO.MedicacionDTO;
import com.medico.historiasclinicas.Entity.Medicacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicacionMapper {

    MedicacionDTO toDto(Medicacion medicacion);

    Medicacion toEntity(MedicacionDTO medicacionDto);
}
