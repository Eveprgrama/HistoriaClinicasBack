
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Actualizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
@Column(nullable = false)
private LocalDate fecha;
    
    @Column(nullable = false)
    private String descripcion;
    
    private String indicaciones;
    
     private String medicacion;
    
    private String droga;
    
    private String dosis;
    
    private Double peso;
    
    private Double altura;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_clinica_id")
    private HistoriaClinica historiaClinica;

    public Actualizacion() {
    }

    public Actualizacion(Long id, LocalDate fecha, String descripcion, String indicaciones, String medicacion, String droga, String dosis, Double peso, Double altura, HistoriaClinica historiaClinica) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.medicacion = medicacion;
        this.droga = droga;
        this.dosis = dosis;
        this.peso = peso;
        this.altura = altura;
        this.historiaClinica = historiaClinica;
    }

    
    
    
}
