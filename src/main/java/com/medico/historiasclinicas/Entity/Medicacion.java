
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicacion")
@Getter
@Setter
public class Medicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMedicacion;
    private String droga;
    private String dosis;

    @ManyToOne
    @JoinColumn(name = "historia_clinica_id")
    private HistoriaClinica historiaClinica;

    public Medicacion() {
    }

    public Medicacion(Long id, String nombreMedicacion, String droga, String dosis, HistoriaClinica historiaClinica) {
        this.id = id;
        this.nombreMedicacion = nombreMedicacion;
        this.droga = droga;
        this.dosis = dosis;
        this.historiaClinica = historiaClinica;
    }
    
    
}
