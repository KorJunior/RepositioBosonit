package com.example.block7crudvalidation.clase.estudiante;

import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteImpRepositorio extends JpaRepository<EstudianteEntity, Long> {
}
