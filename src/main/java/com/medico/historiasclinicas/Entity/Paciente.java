
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter @Setter
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    @Column(nullable = false)
    @NotNull
    private String nombre;
    
    @Column(nullable = false)
    @NotNull
    private String apellido;
    
    @Column(nullable = false, unique = true)
    @NotNull
    private String dni;
    
    @Column(name = "fecha_nacimiento")
    @NotNull
    private LocalDate fechaNacimiento;
    
    private String direccion;
    
    private String telefono;
    
    private String email;
    
    @Column(nullable = false)
    private String role = "ROLE_PACIENTE"; // anotación de rol

    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<HistoriaClinica> historiasClinicas;

    public Paciente() {
    }
    
    public Paciente(Long id) {
    this.id = id;
}

    public Paciente(Long id, String nombre, String apellido, String dni, LocalDate fechaNacimiento, String direccion, String telefono, String email, List<HistoriaClinica> historiasClinicas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.historiasClinicas = historiasClinicas;
    }

    
    
    
}
