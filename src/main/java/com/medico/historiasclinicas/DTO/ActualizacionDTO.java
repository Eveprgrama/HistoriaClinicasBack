package com.medico.historiasclinicas.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActualizacionDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    private String descripcion;
    private String indicaciones;
    private Long historiaClinicaId;
     private String medicacion;
    
    private String droga;
    
    private String dosis;
    
    private Double peso;
    
    private Double altura;

    public ActualizacionDTO() {
    }

    public ActualizacionDTO(Long id, LocalDate fecha, String descripcion, String indicaciones, Long historiaClinicaId, String medicacion, String droga, String dosis, Double peso, Double altura) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.historiaClinicaId = historiaClinicaId;
        this.medicacion = medicacion;
        this.droga = droga;
        this.dosis = dosis;
        this.peso = peso;
        this.altura = altura;
    }

   
}
