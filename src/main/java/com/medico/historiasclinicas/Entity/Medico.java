
package com.medico.historiasclinicas.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter @Setter
public class Medico {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = true)
    private String matricula;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "ROLE_MEDICO"; // anotación de rol

    public Medico() {
    }

    public Medico(Long id, String nombre, String apellido, String matricula, String especialidad, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.password = password;
    }
    
    
}
