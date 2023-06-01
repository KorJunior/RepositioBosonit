package com.example.block7crudvalidation.clase.estudiante.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteInPut;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutFather;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutputFull;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutFather;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
public interface EstudianteService {
    EstudianteOutFather buscarEstudiante(Long id, String outputType);

    List<EstudianteOutFather> listarEstudiante(String outputType);

    EstudianteOutputFull addEstudiante(EstudianteInPut estudianteInPut) throws Exception;
    void borrarEstudiante(Long id);

    EstudianteOutputFull updatePersona(Long id, EstudianteInPut persona);
}
