
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.Paciente;
import com.medico.historiasclinicas.Repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
        @Autowired
    private PacienteRepository pacienteRepository;
        
        public List <Paciente> list(){
            return pacienteRepository.findAll();
        }

    public Optional<Paciente> buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }
    
     public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarPorNombreYApellido(String nombre, String apellido) {
        return pacienteRepository.findByNombreAndApellidoContainingIgnoreCase(nombre, apellido);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminar(Paciente paciente) {
        pacienteRepository.delete(paciente);
    }
}
