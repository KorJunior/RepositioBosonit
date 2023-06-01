package com.example.block7crudvalidation.clase.profesor;


import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorImpRepositorio extends JpaRepository<ProfesorEntity, Long> {
}
