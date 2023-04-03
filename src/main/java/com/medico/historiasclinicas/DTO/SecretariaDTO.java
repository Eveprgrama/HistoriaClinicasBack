package com.medico.historiasclinicas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SecretariaDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String password;
    private String role;

    public SecretariaDTO() {
    }

    public SecretariaDTO(Long id, String nombre, String apellido, String password, String role) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.role = role;
    }
}
