
package com.medico.historiasclinicas.Mapper;

import com.medico.historiasclinicas.DTO.ArchivoHistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArchivoHistoriaClinicaMapper {

    ArchivoHistoriaClinicaDTO toDto(ArchivoHistoriaClinica archivoHistoriaClinica);

    ArchivoHistoriaClinica toEntity(ArchivoHistoriaClinicaDTO archivoHistoriaClinicaDTO);
}
