package com.medico.historiasclinicas.Mapper;

import com.medico.historiasclinicas.DTO.HistoriaClinicaDTO;
import com.medico.historiasclinicas.Entity.HistoriaClinica;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface HistoriaClinicaMapper {
@Mappings({
        @Mapping(source = "paciente.id", target = "pacienteId"),
        // Mapear otras propiedades si es necesario
    })
    HistoriaClinicaDTO toDto(HistoriaClinica historiaClinica);

@InheritInverseConfiguration(name = "toDto")
@Mapping(target = "paciente", ignore = true) // Ignora esta propiedad porque la manejaremos manualmente
HistoriaClinica toEntity(HistoriaClinicaDTO historiaClinicaDTO);
}
