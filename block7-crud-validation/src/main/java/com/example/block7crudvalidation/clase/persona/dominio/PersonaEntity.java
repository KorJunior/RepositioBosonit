package com.example.block7crudvalidation.clase.persona.dominio;

import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFull;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PersonaEntity")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "personaEntity")
    private ProfesorEntity profesorEntity;

    @OneToOne(mappedBy = "personaEntity")
    private EstudianteEntity estudianteEntity;

    public PersonaEntity(PersonaInput personaInputDTO) {

        this.usuario = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.company_email = personaInputDTO.getCompany_email();
        this.personal_email = personaInputDTO.getPersonal_email();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.getActive();
        this.created_date = personaInputDTO.getCreated_date();
        this.imagen_url = personaInputDTO.getImagen_url();
        this.termination_date = personaInputDTO.getTermination_date();
    }

    public PersonaOutPutSimple parsePersonaOutputSimple() {
        PersonaOutPutSimple poDTO = new PersonaOutPutSimple();

        poDTO.setId(this.id);
        poDTO.setUsuario(this.usuario);
        poDTO.setName(this.name);
        poDTO.setSurname(this.surname);
        poDTO.setCompany_email(this.company_email);
        poDTO.setPersonal_email(this.personal_email);
        poDTO.setCity(this.city);
        poDTO.setActive(this.active);
        poDTO.setCreated_date(this.created_date);
        poDTO.setImagen_url(this.imagen_url);
        poDTO.setTermination_date(this.termination_date);
        return poDTO;
    }
    public PersonaOutPutFull parsePersonaOutputFull() {
        PersonaOutPutFull poDTO = new PersonaOutPutFull();

        poDTO.setId(this.id);
        poDTO.setUsuario(this.usuario);
        poDTO.setName(this.name);
        poDTO.setSurname(this.surname);
        poDTO.setCompany_email(this.company_email);
        poDTO.setPersonal_email(this.personal_email);
        poDTO.setCity(this.city);
        poDTO.setActive(this.active);
        poDTO.setCreated_date(this.created_date);
        poDTO.setImagen_url(this.imagen_url);
        poDTO.setTermination_date(this.termination_date);
        poDTO.setProfesorOutputSimple(comprobadorProfesor());
        poDTO.setEstudianteOutPutSimple(comprobadorEstudiante());
        return poDTO;
    }

    private ProfesorOutputSimple comprobadorProfesor() {
        if (profesorEntity != null) {
            return profesorEntity.parseProfesorOutputSimple();
        }
        return null;
    }

    private EstudianteOutPutSimple comprobadorEstudiante() {
        if (estudianteEntity != null) {
            return estudianteEntity.parseEstudianteOutputSimple();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public Date getTermination_date() {
        return termination_date;
    }

    public void setTermination_date(Date termination_date) {
        this.termination_date = termination_date;
    }

    public ProfesorEntity getProfesorEntity() {
        return profesorEntity;
    }

    public void setProfesorEntity(ProfesorEntity profesorEntity) {
        this.profesorEntity = profesorEntity;
    }

    public EstudianteEntity getEstudianteEntity() {
        return estudianteEntity;
    }

    public void setEstudianteEntity(EstudianteEntity estudianteEntity) {
        this.estudianteEntity = estudianteEntity;
    }
}


