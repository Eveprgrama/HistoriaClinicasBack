
package com.medico.historiasclinicas.DTO;

import java.util.List;


public class HistoriaClinicaDTO {

    private Long pacienteId;
    private String enfermedad;
    private String descripcion;
    private String medicacion;
    private String droga;
    private String dosis;
    private Double peso;
    private Double altura;
    private String indicaciones;
    private List<ActualizacionDTO> actualizaciones;
    private List<ArchivoHistoriaClinicaDTO> archivos;
    
    public HistoriaClinicaDTO() {
    }

    public List<ActualizacionDTO> getActualizaciones() {
        return actualizaciones;
    }

    public void setActualizaciones(List<ActualizacionDTO> actualizaciones) {
        this.actualizaciones = actualizaciones;
    }

    public List<ArchivoHistoriaClinicaDTO> getArchivos() {
        return archivos;
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

        public String getMedicacion() {
            return medicacion;
        }

        public void setMedicacion(String medicacion) {
            this.medicacion = medicacion;
        }

        public String getDroga() {
            return droga;
        }

        public void setDroga(String droga) {
            this.droga = droga;
        }

        public String getDosis() {
            return dosis;
        }

        public void setDosis(String dosis) {
            this.dosis = dosis;
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






