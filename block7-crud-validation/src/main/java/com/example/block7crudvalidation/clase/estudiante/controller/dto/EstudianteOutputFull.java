package com.example.block7crudvalidation.clase.estudiante.controller.dto;

import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutputFull implements EstudianteOutFather{

    private Long idEstudiante;
    private String coments;
    private int num_hours_week;
    private PersonaOutPut estudiante;
    private ProfesorOutputSimple profesor;
    private List<EstudianteAsignaturaOutPutSimple> estudios;

}
