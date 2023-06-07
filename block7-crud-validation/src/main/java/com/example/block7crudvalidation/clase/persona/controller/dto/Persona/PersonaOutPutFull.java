package com.example.block7crudvalidation.clase.persona.controller.dto.Persona;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutPutFull implements PersonaOutPutFather {
    private Long id;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private ProfesorOutputSimple profesorOutputSimple;
    private EstudianteOutPutSimple estudianteOutPutSimple;
}
