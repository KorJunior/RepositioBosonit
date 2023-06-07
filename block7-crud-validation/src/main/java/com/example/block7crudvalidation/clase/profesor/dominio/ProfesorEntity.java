package com.example.block7crudvalidation.clase.profesor.dominio;


import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputFull;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProfesorEntity")
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesor;

    @NotNull
    private String branch;

    @OneToOne
    @JoinColumn(name = "fk_persona_profesor", referencedColumnName = "id")
    private PersonaEntity personaEntity;

    @OneToMany(mappedBy = "profesorEntity")
    private List<EstudianteEntity> estudianteEntities;


    public ProfesorOutputSimple parseProfesorOutputSimple() {
        ProfesorOutputSimple profesorOutputSimple = new ProfesorOutputSimple();

        profesorOutputSimple.setIdProfesor(this.id_profesor);
        profesorOutputSimple.setBranch(this.branch);
        profesorOutputSimple.setPersonaOutPutSimple(this.personaEntity.parsePersonaOutputSimple());
        return profesorOutputSimple;
    }
    public ProfesorOutputFull parseProfesorOutputFull() {
        ProfesorOutputFull profesorOutputFull = new ProfesorOutputFull();

        profesorOutputFull.setIdProfesor(this.id_profesor);
        profesorOutputFull.setBranch(this.branch);
        profesorOutputFull.setPersonaOutPutSimple(this.personaEntity.parsePersonaOutputSimple());
        profesorOutputFull.setEstudianteEntities(convertirLista());
        return profesorOutputFull;
    }

    public List<EstudianteOutPutSimple> convertirLista() {
        List<EstudianteOutPutSimple> estudianteOutPutSimples = new ArrayList<>();

        if (estudianteEntities != null) {
            for (EstudianteEntity estudianteEntity : estudianteEntities) {
                estudianteOutPutSimples.add(estudianteEntity.parseEstudianteOutputSimple());
            }
        }
        return estudianteOutPutSimples;
    }

}
