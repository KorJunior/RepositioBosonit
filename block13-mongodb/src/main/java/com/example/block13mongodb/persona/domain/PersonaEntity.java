package com.example.block13mongodb.persona.domain;

import com.example.block13mongodb.persona.controller.dto.PersonaInput;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutFull;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "personas") // Nombre de la colección en MongoDB
public class PersonaEntity {
    @Id
    private String id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public PersonaEntity(PersonaInput personaInputDTO) {
        // Constructor

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
        return poDTO;
    }
}
