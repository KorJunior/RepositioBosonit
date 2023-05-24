package com.example.block7crudvalidation;

import com.example.block7crudvalidation.Clases.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PersonaRepositorio extends JpaRepository<Persona, Long> {
}
