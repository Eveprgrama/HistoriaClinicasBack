
package com.medico.historiasclinicas.security.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NuevoUsuario {
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
    private boolean esMedico;
    private boolean esSecretaria;

    public NuevoUsuario() {
    }

    public NuevoUsuario(String nombre, String nombreUsuario, String email, String password, boolean esMedico, boolean esSecretaria) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.esMedico = esMedico;
        this.esSecretaria = esSecretaria;
    }
    
    
    
    public boolean isEsMedico() {
        return esMedico;
    }

    public void setEsMedico(boolean esMedico) {
        this.esMedico = esMedico;
    }

    public boolean isEsSecretaria() {
        return esSecretaria;
    }

    public void setEsSecretaria(boolean esSecretaria) {
        this.esSecretaria = esSecretaria;
    }
    
    public Set<String> getRoles() {
        roles.add(esMedico ? "ROLE_MEDICO" : "");
        roles.add(esSecretaria ? "ROLE_SECRETARIA" : "");
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

