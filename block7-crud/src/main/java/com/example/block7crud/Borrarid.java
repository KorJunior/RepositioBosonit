package com.example.block7crud;

import com.example.block7crud.dominio.Persona;
import com.example.block7crud.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/URL")
public class Borrarid {
    @Autowired
    private PersonaRepositorio personaRepository;

    @DeleteMapping("/persona/{id}")
    public Persona updatePersona(@PathVariable Long id) {
        Persona persona,aux;

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

         persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        aux=persona;
        personaRepository.delete(persona);
        return aux;


    }

}
