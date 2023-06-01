package com.example.block7crudvalidation.clase.persona.aplicacion;

import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonaService {
    PersonaOutPut buscarPersonaID(Long id);

    List<PersonaOutPut> listarPersonas();

    PersonaOutPut addPersona(PersonaInput personaInput) throws Exception;
    void deletePersonaById(Long id);

    PersonaOutPut updatePersona(Long id,PersonaInput persona);

}

