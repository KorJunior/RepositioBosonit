package com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteAsignaturaOutPutFull implements EstudianteAsignaturaOutFather {
    private Long id_asignatura;
    private String asignatura;
    private String coments;
    private Date initial_date;
    private Date finish_date;
    private List<EstudianteOutPutSimple> estudiantesEntities;
}
