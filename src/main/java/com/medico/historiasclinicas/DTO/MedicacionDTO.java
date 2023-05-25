package com.medico.historiasclinicas.DTO;

import com.medico.historiasclinicas.Entity.Medicacion;

public class MedicacionDTO {

    private String nombreMedicacion;
    private String droga;
    private String dosis;

    public MedicacionDTO(Medicacion medicacion) {
    }

    public MedicacionDTO(String nombreMedicacion, String droga, String dosis) {
        this.nombreMedicacion = nombreMedicacion;
        this.droga = droga;
        this.dosis = dosis;
    }

    
    public String getNombreMedicacion() {
        return nombreMedicacion;
    }

    public void setNombreMedicacion(String nombreMedicacion) {
        this.nombreMedicacion = nombreMedicacion;
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

}
