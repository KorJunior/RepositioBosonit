package com.example.block7crudvalidation.clase.estudiante.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteInPut {


    private Long id_persona;
    private Long id_profesor;
    private String coments;
    private int num_hours_week;
}
