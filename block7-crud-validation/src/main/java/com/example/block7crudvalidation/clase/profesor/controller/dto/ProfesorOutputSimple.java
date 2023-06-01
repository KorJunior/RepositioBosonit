package com.example.block7crudvalidation.clase.profesor.controller.dto;

import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputSimple implements ProfesorOutPutFather{
    private Long idProfesor;
    private String branch;
    private PersonaOutPut personaOutPut;

}
