package com.example.parcial2.repositories;

import com.example.parcial2.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface docentesRepository extends JpaRepository<Medico, Long> {
}