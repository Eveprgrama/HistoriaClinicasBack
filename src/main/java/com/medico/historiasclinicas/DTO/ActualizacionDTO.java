package com.medico.historiasclinicas.DTO;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActualizacionDTO {

    private Long id;
    private Date fecha;
    private String descripcion;
    private Long historiaClinicaId;

    public ActualizacionDTO() {
    }

    public ActualizacionDTO(Long id, Date fecha, String descripcion, Long historiaClinicaId) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.historiaClinicaId = historiaClinicaId;
    }
}
