package com.example.block13mongodb.persona.aplication;

import com.example.block13mongodb.persona.controller.dto.PersonaInput;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutFather;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutSimple;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface PersonaServicio {
    PersonaOutPutSimple updatePersona(@PathVariable String id, @RequestBody PersonaInput personaData);

    PersonaOutPutSimple buscarPersonaID(String id);

    List<PersonaOutPutFather> listarPersonas(String outputType);

    PersonaOutPutSimple addPersona(PersonaInput personaInput) throws Exception;
    void deletePersonaById(String id);


}
