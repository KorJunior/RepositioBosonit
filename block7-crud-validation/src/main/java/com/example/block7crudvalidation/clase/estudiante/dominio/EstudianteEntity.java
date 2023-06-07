package com.example.block7crudvalidation.clase.estudiante.dominio;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutputFull;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import com.example.block7crudvalidation.clase.estudianteAsignatura.dominio.EstudianteAsignaturaEntity;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EstudianteEntity")
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estudiante;

    @NotNull
    private int num_hours_week;


    private String coments;

    @OneToOne
    @JoinColumn(name = "fk_persona_estudiante", referencedColumnName = "id")
    private PersonaEntity personaEntity;

    @ManyToOne
    @JoinColumn(name = "fk_profesor_estudiante", referencedColumnName = "id_profesor")
    private ProfesorEntity profesorEntity;

    @ManyToMany
    private List<EstudianteAsignaturaEntity> estudios;



    public EstudianteOutputFull parseEstudianteOutputFull() {
        EstudianteOutputFull estudianteOutputFull = new EstudianteOutputFull();

        estudianteOutputFull.setIdEstudiante(this.id_estudiante);
        estudianteOutputFull.setComents(this.coments);
        estudianteOutputFull.setEstudiante(this.personaEntity.parsePersonaOutputSimple());
        estudianteOutputFull.setNum_hours_week(this.num_hours_week);
        estudianteOutputFull.setProfesor(this.profesorEntity.parseProfesorOutputSimple());
        estudianteOutputFull.setEstudios(convertirLista());
        return estudianteOutputFull;
    }
    public EstudianteOutPutSimple parseEstudianteOutputSimple() {
        EstudianteOutPutSimple estudianteOutputSimple = new EstudianteOutPutSimple();

        estudianteOutputSimple.setIdEstudiante(this.id_estudiante);
        estudianteOutputSimple.setComents(this.coments);
        estudianteOutputSimple.setEstudiante(this.personaEntity.parsePersonaOutputSimple());
        estudianteOutputSimple.setNum_hours_week(this.num_hours_week);
        return estudianteOutputSimple;
    }





    public List<EstudianteAsignaturaOutPutSimple> convertirLista() {
        List<EstudianteAsignaturaOutPutSimple> estudiantesOutputs = new ArrayList<>();

        if (estudios != null) {
            for (EstudianteAsignaturaEntity estudio : estudios) {
                estudiantesOutputs.add(estudio.parseAsignaturaOutputSimple());
            }
        }
        return estudiantesOutputs;
    }

    public Long getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Long id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getNum_hours_week() {
        return num_hours_week;
    }

    public void setNum_hours_week(int num_hours_week) {
        this.num_hours_week = num_hours_week;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }

    public PersonaEntity getPersonaEntity() {
        return personaEntity;
    }

    public void setPersonaEntity(PersonaEntity personaEntity) {
        this.personaEntity = personaEntity;
    }

    public ProfesorEntity getProfesorEntity() {
        return profesorEntity;
    }

    public void setProfesorEntity(ProfesorEntity profesorEntity) {
        this.profesorEntity = profesorEntity;
    }

    public List<EstudianteAsignaturaEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudianteAsignaturaEntity> estudios) {
        this.estudios = estudios;
    }
}
