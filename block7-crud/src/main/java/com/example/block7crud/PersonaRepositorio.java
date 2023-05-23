package com.example.block7crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PersonaRepositorio extends JpaRepository<Persona, Long> {
}
