package com.example.block7crud;

import com.example.block7crud.dominio.Persona;
import com.example.block7crud.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/URL")
public class GestionPersonas {

    @Autowired
    private PersonaRepositorio personaRepository;

    @PostMapping("/persona")
    public Persona addPersona(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    @GetMapping("/personas")
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    @GetMapping("/persona/{id}")
        public Persona buscarPersonaID (@PathVariable Long id) {
        Persona persona;

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return persona;

        }
    }

