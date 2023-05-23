package com.example.block7crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persona")
public class BuscarNombre {

    @Autowired
    private PersonaRepositorio personaRepository;

    @GetMapping("/nombre/{nombre}")
    public List<Persona> buscarPersonaID(@PathVariable String nombre) {
        List<Persona> personas, personasFiltrada;

        if (nombre == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        personas = personaRepository.findAll();
        personasFiltrada = personas.stream()
                .filter(persona -> persona.getNombre().equals(nombre))
                .collect(Collectors.toList());


        return personasFiltrada;

    }

}
