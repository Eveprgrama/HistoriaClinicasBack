package com.medico.historiasclinicas.Service;

import com.medico.historiasclinicas.Entity.Medico;
import com.medico.historiasclinicas.Repository.MedicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> buscarTodos() {
        return medicoRepository.findAll();
    }

    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void eliminar(Medico medico) {
        medicoRepository.delete(medico);
    }
}
