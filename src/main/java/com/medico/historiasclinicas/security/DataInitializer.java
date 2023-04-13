/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medico.historiasclinicas.security;

import com.medico.historiasclinicas.security.Entity.Rol;
import com.medico.historiasclinicas.security.Entity.Usuario;
import com.medico.historiasclinicas.security.Enums.RolNombre;
import com.medico.historiasclinicas.security.repository.iRolRepository;
import com.medico.historiasclinicas.security.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iRolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si la base de datos ya existe
        if (!databaseExists()) {
            // Crear roles
            Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
            Rol rolUser = new Rol(RolNombre.ROLE_USER);
            Rol rolMedico = new Rol(RolNombre.ROLE_MEDICO);
            Rol rolSecretaria = new Rol(RolNombre.ROLE_SECRETARIA);

            // Guardar roles
            rolRepository.save(rolAdmin);
            rolRepository.save(rolUser);
            rolRepository.save(rolMedico);
            rolRepository.save(rolSecretaria);

            // Crear usuarios con roles asignados
            Usuario usuarioAdmin = new Usuario("admin", "admin", "admin@gmail.com", "123456");
            usuarioAdmin.getRoles().add(rolAdmin);

            Usuario usuarioUser = new Usuario("user", "user", "user@gmail.com", "123456");
            usuarioUser.getRoles().add(rolUser);

            Usuario usuarioMedico = new Usuario("medico", "medico", "medico@gmail.com", "123456");
            usuarioMedico.getRoles().add(rolMedico);

            Usuario usuarioSecretaria = new Usuario("secretaria", "secretaria", "secretaria@gmail.com", "123456");
            usuarioSecretaria.getRoles().add(rolSecretaria);

            // Guardar usuarios
            usuarioRepository.save(usuarioAdmin);
            usuarioRepository.save(usuarioUser);
            usuarioRepository.save(usuarioMedico);
            usuarioRepository.save(usuarioSecretaria);
        }
    }

    private boolean databaseExists() {
        // Verificar si la base de datos ya existe
        long count = usuarioRepository.count();
    return count > 0;
    }
}

