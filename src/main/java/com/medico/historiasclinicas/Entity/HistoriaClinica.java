package com.medico.historiasclinicas.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historias_clinicas")
@Getter
@Setter
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    private String enfermedad;

    private String descripcion;

    private String medicacion;
    
    private String peso;
    
    private String altura;

    @Column(nullable = false)
    private String indicaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL)
    private List<ArchivoHistoriaClinica> archivosHistoriaClinica;

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actualizacion> actualizaciones = new ArrayList<>();

    @OneToMany(mappedBy = "historiaClinica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoHistoriaClinica> archivos = new ArrayList<>();

    public HistoriaClinica() {
    }

    public HistoriaClinica(Long id, LocalDateTime fechaCreacion, String enfermedad, String descripcion, String medicacion, String peso, String altura, String indicaciones, Paciente paciente, List<ArchivoHistoriaClinica> archivosHistoriaClinica, List<Actualizacion> actualizaciones) {
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
    }

}
