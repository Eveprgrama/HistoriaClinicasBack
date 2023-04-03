package com.medico.historiasclinicas.DTO;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private List<HistoriaClinicaDTO> historiasClinicas;

    public PacienteDTO() {
    }

    public PacienteDTO(Long id, String nombre, String apellido, String dni, LocalDate fechaNacimiento,
            String direccion, String telefono, String email, List<HistoriaClinicaDTO> historiasClinicas) {
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
