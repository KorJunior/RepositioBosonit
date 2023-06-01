package com.example.block7crudvalidation.clase.persona;

import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaImpRepositorio extends JpaRepository<PersonaEntity, Long> {
}
