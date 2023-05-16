package com.medico.historiasclinicas.DTO;

import com.medico.historiasclinicas.Entity.ArchivoHistoriaClinica;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivoHistoriaClinicaDTO {

    private Long id;
    private String nombre;
    private String tipo;
    private String url;
    private Long historiaClinicaId;

    public ArchivoHistoriaClinicaDTO() {
    }

    public ArchivoHistoriaClinicaDTO(Long id, String nombre, String tipo, String url, Long historiaClinicaId) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.url = url;
        this.historiaClinicaId = historiaClinicaId;
    }
    
     // constructor nuevo
    public ArchivoHistoriaClinicaDTO(ArchivoHistoriaClinica archivo) {
        this.id = archivo.getId();
        this.nombre = archivo.getNombre();
        this.tipo = archivo.getTipo();
        this.url = "http://localhost:8080/archivos/" + archivo.getId();
        this.historiaClinicaId = archivo.getHistoriaClinica().getId();
    }
}
