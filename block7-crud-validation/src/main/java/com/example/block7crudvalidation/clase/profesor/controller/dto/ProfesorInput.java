package com.example.block7crudvalidation.clase.profesor.controller.dto;

import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorInput {

    private Long id;
    private String branch;

}
