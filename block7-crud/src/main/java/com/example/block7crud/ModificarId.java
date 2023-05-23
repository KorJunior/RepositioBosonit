package com.example.block7crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/URL")
public class ModificarId {

    @Autowired
    private PersonaRepositorio personaRepository;

    @PutMapping("/persona/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaData) {
        Persona persona;

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (personaData.getNombre() != null && personaData.getPoblacion() != null && personaData.getEdad() != null) {
            persona.setNombre(personaData.getNombre());
            persona.setEdad(personaData.getEdad());
            persona.setPoblacion(personaData.getPoblacion());

            return personaRepository.save(persona);
        } else {
            return null;
        }
    }




}
