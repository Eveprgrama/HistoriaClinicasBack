
package com.medico.historiasclinicas.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HistoriaClinicaDTO {
 private Long id;
    private LocalDateTime fechaCreacion;
    private String enfermedad;
    private String descripcion;
    private String medicacion;
    private String peso;
    private String altura;
    private String indicaciones;
    private PacienteDTO paciente;
    private List<ArchivoHistoriaClinicaDTO> archivosHistoriaClinica;
    private List<ActualizacionDTO> actualizaciones;
    private List<ArchivoHistoriaClinicaDTO> archivos;

    public HistoriaClinicaDTO() {
    }

   public HistoriaClinicaDTO(Long id, LocalDateTime fechaCreacion, String enfermedad, String descripcion, String medicacion, String peso, String altura, String indicaciones, PacienteDTO paciente, List<ArchivoHistoriaClinicaDTO> archivosHistoriaClinica, List<ActualizacionDTO> actualizaciones, List<ArchivoHistoriaClinicaDTO> archivos) {
    this.id = id;
    this.fechaCreacion = fechaCreacion;
    this.enfermedad = enfermedad;
    this.descripcion = descripcion;
    this.medicacion = medicacion;
    this.peso = peso;
    this.altura = altura;
    this.indicaciones = indicaciones;
    this.paciente = paciente;
    this.archivosHistoriaClinica = archivosHistoriaClinica;
    this.actualizaciones = actualizaciones;
    this.archivos = archivos;
}
    
    
}
