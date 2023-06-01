package com.example.block7crudvalidation.clase.estudianteAsignatura.aplicacion;

import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaInput;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutFather;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EstudianteAsignaturaService {

    EstudianteAsignaturaOutFather buscarAsignatura(Long id,String outputType);

    List<EstudianteAsignaturaOutFather> listarAsignatura(String outputType);

    EstudianteAsignaturaOutPutSimple añadirAsignatura(EstudianteAsignaturaInput estudianteAsignaturaInput) throws Exception;
    void borrarAsignarura(Long id);

    EstudianteAsignaturaOutPutSimple updateAsignatura(Long id, EstudianteAsignaturaInput asignaturaInput);
}
