
package com.medico.historiasclinicas.Mapper;

import com.medico.historiasclinicas.DTO.ActualizacionDTO;
import com.medico.historiasclinicas.Entity.Actualizacion;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ActualizacionMapper {
     ActualizacionDTO toDto(Actualizacion actualizacion);

    Actualizacion toEntity(ActualizacionDTO actualizacionDTO);
}
