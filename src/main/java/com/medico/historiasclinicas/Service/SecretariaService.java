
package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.Secretaria;
import com.medico.historiasclinicas.Repository.SecretariaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretariaService {
        @Autowired
    private SecretariaRepository secretariaRepository;

    public Secretaria buscarPorId(Long id) {
        return secretariaRepository.findById(id).orElse(null);
    }

    public List<Secretaria> buscarTodos() {
        return secretariaRepository.findAll();
    }

    public Secretaria guardar(Secretaria secretaria) {
        return secretariaRepository.save(secretaria);
    }

    public void eliminar(Secretaria secretaria) {
        secretariaRepository.delete(secretaria);
    }
}
