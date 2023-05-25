package com.medico.historiasclinicas.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

public class HistoriaClinicaDTO {

    private Long pacienteId;
    private String fechaCreacion;
    private String enfermedad;
    private String descripcion;
    private List<MedicacionDTO> medicacion;
    private Double peso;
    private Double altura;
    private String indicaciones;
    private List<ActualizacionDTO> actualizaciones;
    private List<ArchivoHistoriaClinicaDTO> archivos;

    public HistoriaClinicaDTO() {
    }

    public HistoriaClinicaDTO(Long pacienteId, String fechaCreacion, String enfermedad, String descripcion, List<MedicacionDTO> medicacion, Double peso, Double altura, String indicaciones, List<ActualizacionDTO> actualizaciones, List<ArchivoHistoriaClinicaDTO> archivos) {
        this.pacienteId = pacienteId;
        this.fechaCreacion = fechaCreacion;
        this.enfermedad = enfermedad;
        this.descripcion = descripcion;
        this.medicacion = medicacion;
        this.peso = peso;
        this.altura = altura;
        this.indicaciones = indicaciones;
        this.actualizaciones = actualizaciones;
        this.archivos = archivos;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (fechaCreacion != null && !fechaCreacion.isEmpty()) {
            try {
                LocalDate fecha = formatter.parse(fechaCreacion, LocalDate::from);
                this.fechaCreacion = formatter.format(fecha);
            } catch (DateTimeParseException e) {
                this.fechaCreacion = LocalDate.now().format(formatter);
            }
        } else {
            this.fechaCreacion = LocalDate.now().format(formatter);
        }
    }

    public List<ActualizacionDTO> getActualizaciones() {
        if (actualizaciones == null) {
            return Collections.emptyList();
        }
        return actualizaciones;
    }

    public List<ArchivoHistoriaClinicaDTO> getArchivos() {
        if (archivos == null) {
            return Collections.emptyList();
        }
        return archivos;
    }

    public void setActualizaciones(List<ActualizacionDTO> actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public void setArchivos(List<ArchivoHistoriaClinicaDTO> archivos) {
        this.archivos = archivos;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MedicacionDTO> getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(List<MedicacionDTO> medicacion) {
        this.medicacion = medicacion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

}
