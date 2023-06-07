package com.example.block7crudvalidation.clase.profesor.controller.dto;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputFull implements ProfesorOutPutFather{
    private Long idProfesor;
    private String branch;
    private PersonaOutPutSimple personaOutPutSimple;
    private List<EstudianteOutPutSimple> estudianteEntities;
}
