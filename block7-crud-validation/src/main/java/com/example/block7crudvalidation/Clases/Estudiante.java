package com.example.block7crudvalidation.Clases;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estudiante;

    @NotNull
    private int num_hours_week;

    @NotNull
    private String branch;

    @OneToOne
    @JoinColumn(name = "fk_persona_estudiante", referencedColumnName = "id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "fk_profesor_estudiante", referencedColumnName = "id_profesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "estudiante")
    private List<Estudiante_asignatura> estudios;
}
