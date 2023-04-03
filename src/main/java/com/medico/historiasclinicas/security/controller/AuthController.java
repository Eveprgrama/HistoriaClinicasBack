
package com.medico.historiasclinicas.security.controller;

import com.medico.historiasclinicas.security.Entity.Rol;
import com.medico.historiasclinicas.security.Entity.Usuario;
import com.medico.historiasclinicas.security.Enums.RolNombre;
import com.medico.historiasclinicas.security.dto.JwtDto;
import com.medico.historiasclinicas.security.dto.NuevoUsuario;
import com.medico.historiasclinicas.security.jwt.JwtProvider;
import com.medico.historiasclinicas.security.service.RolService;
import com.medico.historiasclinicas.security.service.UsuarioService;
import com.medico.historiasclinicas.security.dto.LoginUsuario;
import java.util.HashSet;
import java.util.Set;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
     @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
   @PostMapping("/nuevo")
public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
    if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
        return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
    if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
        return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
    Usuario usuario =
            new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                    passwordEncoder.encode(nuevoUsuario.getPassword()));
    Set<String> rolesStr = nuevoUsuario.getRoles();
    Set<Rol> roles = new HashSet<>();
    for (String rol : rolesStr) {
        switch (rol) {
            case "admin" -> {
                Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
                roles.add(rolAdmin);
            }
            case "secretaria" -> {
                Rol rolSecretaria = rolService.getByRolNombre(RolNombre.ROLE_SECRETARIA).get();
                roles.add(rolSecretaria);
            }
            case "medico" -> {
                Rol rolMedico = rolService.getByRolNombre(RolNombre.ROLE_MEDICO).get();
                roles.add(rolMedico);
            }
            default -> {
                Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                roles.add(rolUser);
            }
        }
    }
    usuario.setRoles(roles);
    usuarioService.save(usuario);
    return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
}

    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
}
