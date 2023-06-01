package com.example.block7crudvalidation.clase.persona.controller;

import com.example.block7crudvalidation.clase.persona.aplicacion.PersonaServiceImpl;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/personas")
@RestController
public class ControladorPersona {


    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping("/personaid/{id}")
    public PersonaOutPut buscarPersonaID(@PathVariable Long id) {
        return personaService.buscarPersonaID(id);
    }
    @GetMapping("/personas")
    public List<PersonaOutPut> listarPersonas() {
        return personaService.listarPersonas();
    }
    @PostMapping("/personas")
    public PersonaOutPut addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        return personaService.addPersona(personaInput);
    }
    @DeleteMapping("/personas/{id}")
    public void deletePersonaById(@PathVariable Long id) {
        personaService.deletePersonaById(id);
    }
    @PutMapping("/personas/{id}")
    public PersonaOutPut updatePersona(@PathVariable Long id, @RequestBody PersonaInput personaInput) {
        return personaService.updatePersona(id, personaInput);
    }
    @GetMapping("/{nombre}")
    public PersonaOutPut getPersonaPorNombre(@PathVariable String nombre) {
            return personaService.getPersonaPorNombre(nombre);
    }






}
