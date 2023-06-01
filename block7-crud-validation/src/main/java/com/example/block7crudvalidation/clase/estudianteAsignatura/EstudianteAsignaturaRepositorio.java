package com.example.block7crudvalidation.clase.estudianteAsignatura;

import com.example.block7crudvalidation.clase.estudianteAsignatura.dominio.EstudianteAsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteAsignaturaRepositorio extends JpaRepository<EstudianteAsignaturaEntity, Long> {
}
