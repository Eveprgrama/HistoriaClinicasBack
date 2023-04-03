/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medico.historiasclinicas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private String especialidad;

    public MedicoDTO() {
    }

    public MedicoDTO(Long id, String nombre, String apellido, String matricula, String especialidad, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.especialidad = especialidad;
    }

}
