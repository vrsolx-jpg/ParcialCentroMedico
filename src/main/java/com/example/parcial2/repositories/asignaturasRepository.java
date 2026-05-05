package com.example.parcial2.repositories;

import com.example.parcial2.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface asignaturasRepository extends JpaRepository<Consulta, Long> {
}