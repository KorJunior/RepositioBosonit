package com.example.block7crudvalidation.clase.estudianteAsignatura.dominio;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaInput;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutFull;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EstudianteAsignaturaEntity")
public class EstudianteAsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_asignatura;

    @ManyToMany
    @JoinColumn(name = "fk_estudiante", referencedColumnName = "id_estudiante")
    private List<EstudianteEntity> estudiantesEntities;

    private String asignatura;
    private String coments;
    @NotNull
    private Date initial_date;
    private Date finish_date;

    public EstudianteAsignaturaEntity(EstudianteAsignaturaInput estudianteAsignaturaInput) {
        this.asignatura = estudianteAsignaturaInput.getAsignatura();
        this.coments = estudianteAsignaturaInput.getComents();
        this.initial_date = estudianteAsignaturaInput.getInitial_date();
        this.finish_date = estudianteAsignaturaInput.getFinish_date();
    }

    public EstudianteAsignaturaOutPutSimple parseAsignaturaOutputSimple() {
        EstudianteAsignaturaOutPutSimple estudianteAsignaturaOutPutSimple = new EstudianteAsignaturaOutPutSimple();

        estudianteAsignaturaOutPutSimple.setId_asignatura(this.id_asignatura);
        estudianteAsignaturaOutPutSimple.setAsignatura(this.asignatura);
        estudianteAsignaturaOutPutSimple.setComents(this.coments);
        estudianteAsignaturaOutPutSimple.setInitial_date(this.initial_date);
        estudianteAsignaturaOutPutSimple.setFinish_date(this.finish_date);
        return estudianteAsignaturaOutPutSimple;
    }
    public EstudianteAsignaturaOutPutFull parseAsignaturaOutputFull() {
        EstudianteAsignaturaOutPutFull estudianteAsignaturaOutPutFull = new EstudianteAsignaturaOutPutFull();

        estudianteAsignaturaOutPutFull.setId_asignatura(this.id_asignatura);
        estudianteAsignaturaOutPutFull.setAsignatura(this.asignatura);
        estudianteAsignaturaOutPutFull.setComents(this.coments);
        estudianteAsignaturaOutPutFull.setInitial_date(this.initial_date);
        estudianteAsignaturaOutPutFull.setFinish_date(this.finish_date);
        estudianteAsignaturaOutPutFull.setEstudiantesEntities(convertirLista());
        return estudianteAsignaturaOutPutFull;
    }
    public List<EstudianteOutPutSimple> convertirLista() {
        List<EstudianteOutPutSimple> estudianteOutPutSimples = new ArrayList<>();

        if (estudiantesEntities != null) {
            for (EstudianteEntity estudianteEntity : estudiantesEntities) {
                estudianteOutPutSimples.add(estudianteEntity.parseEstudianteOutputSimple());
            }
        }
        return estudianteOutPutSimples;
    }



    public Long getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Long id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public List<EstudianteEntity> getEstudiantesEntities() {
        return estudiantesEntities;
    }

    public void setEstudiantesEntities(List<EstudianteEntity> estudiantesEntities) {
        this.estudiantesEntities = estudiantesEntities;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String nota) {
        this.coments = nota;
    }

    public Date getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(Date initial_date) {
        this.initial_date = initial_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }
}



