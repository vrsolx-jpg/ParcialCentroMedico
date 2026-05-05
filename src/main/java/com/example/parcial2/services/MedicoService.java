package com.example.parcial2.services;

import com.example.parcial2.entities.Medico;
import com.example.parcial2.repositories.docentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private docentesRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
