package com.example.parcial2.services;

import com.example.parcial2.entities.Consulta;
import com.example.parcial2.repositories.asignaturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private asignaturasRepository consultaRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta obtenerPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public Consulta guardar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void eliminar(Long id) {
        consultaRepository.deleteById(id);
    }
}
