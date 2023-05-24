package com.example.block7crudvalidation.Clases;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante_asignatura")
public class Estudiante_asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asignatura;

    @ManyToOne
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id_estudiante")
    private Estudiante estudiante;

    private String asignatura;

    @NotNull
    private Date initial_date;
    private Date finish_date;
}

