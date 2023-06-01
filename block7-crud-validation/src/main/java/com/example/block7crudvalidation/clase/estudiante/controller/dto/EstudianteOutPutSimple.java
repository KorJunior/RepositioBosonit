package com.example.block7crudvalidation.clase.estudiante.controller.dto;

import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteOutPutSimple implements EstudianteOutFather{
    private Long idEstudiante;
    private String coments;
    private int num_hours_week;
    private PersonaOutPut estudiante;

}
