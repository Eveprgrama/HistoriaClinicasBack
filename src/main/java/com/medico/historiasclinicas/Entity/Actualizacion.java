
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Actualizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Date fecha;
    
    @Column(nullable = false)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_clinica_id")
    private HistoriaClinica historiaClinica;

    public Actualizacion() {
    }

    public Actualizacion(Long id, Date fecha, String descripcion, HistoriaClinica historiaClinica) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.historiaClinica = historiaClinica;
    }
    
    
}
