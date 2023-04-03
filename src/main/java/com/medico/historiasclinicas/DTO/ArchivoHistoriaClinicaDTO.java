package com.medico.historiasclinicas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivoHistoriaClinicaDTO {

    private Long id;
    private String nombre;
    private String tipo;
    private String archivo;
    private Long historiaClinicaId;

    public ArchivoHistoriaClinicaDTO() {
    }

    public ArchivoHistoriaClinicaDTO(Long id, String nombre, String tipo, String archivo, Long historiaClinicaId) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.archivo = archivo;
        this.historiaClinicaId = historiaClinicaId;
    }
}
