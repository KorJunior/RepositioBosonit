package com.example.block7crudvalidation.clase.profesor.aplicacion;

import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorInput;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutPutFather;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfesorService {
    ProfesorOutputSimple buscarProfesor(Long id);

    List<ProfesorOutPutFather> listarProfesores(String outputType);

    ProfesorOutputSimple addProfesor(ProfesorInput profesorInput) throws Exception;
    void deletePersonaById(Long id);

    ProfesorOutputSimple updatePersona(Long id, ProfesorInput persona);
}
