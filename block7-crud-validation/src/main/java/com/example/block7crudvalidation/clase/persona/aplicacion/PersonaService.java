package com.example.block7crudvalidation.clase.persona.aplicacion;

import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFather;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonaService {
    PersonaOutPutSimple buscarPersonaID(Long id);

    List<PersonaOutPutFather> listarPersonas(String outputType);

    PersonaOutPutSimple addPersona(PersonaInput personaInput) throws Exception;
    void deletePersonaById(Long id);

    PersonaOutPutSimple updatePersona(Long id, PersonaInput persona);

}

