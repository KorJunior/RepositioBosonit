package com.example.block7crudvalidation.Clases;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 6, max = 10, message = "El nombre debe contener entre 6 y 10 caracteres")
    @NotNull
    private String usuario;

    @NotNull
    private String password;

    @NotNull
    private String name;
    private String surname;

    @NotNull
    private String company_email;

    @NotNull
    private String personal_email;

    @NotNull
    private String city;

    @NotNull
    private Boolean active;

    @NotNull
    private Date created_date;

    private String imagen_url;
    private Date termination_date;

    @OneToOne(mappedBy = "persona")
    private Profesor profesor;

    @OneToOne(mappedBy = "persona")
    private Estudiante estudiante;
}

