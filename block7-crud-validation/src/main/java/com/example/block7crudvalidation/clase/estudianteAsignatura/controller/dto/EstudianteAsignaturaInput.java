package com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteAsignaturaInput {


    private Long id_estudiante;
    private String asignatura;
    private String coments;
    private Date initial_date;
    private Date finish_date;
}
