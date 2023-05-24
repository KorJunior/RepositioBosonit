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
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesor;

    @NotNull
    private String branch;

    @OneToOne
    @JoinColumn(name = "fk_persona_profesor", referencedColumnName = "id")
    private Persona persona;

    @OneToMany(mappedBy = "profesor")
    private List<Estudiante> estudiantes;
}

