package com.example.block7crudvalidation.clase.persona.controller;

import com.example.block7crudvalidation.clase.persona.aplicacion.PersonaServiceImpl;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFather;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ControladorPersona {
    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping("/personaid/{id}")
    public PersonaOutPutSimple buscarPersonaID(@PathVariable Long id) {
        return personaService.buscarPersonaID(id);
    }
    @GetMapping("/personas")
    public List<PersonaOutPutFather> listarPersonas(String outputType) {
        return personaService.listarPersonas(outputType);
    }
    @GetMapping("/getall")
    public List<PersonaOutPutFather> listarPersonasPagina(@RequestParam(required = false, defaultValue = "full") String outputType) {
        return personaService.listarPersonas(outputType);
    }
    @PostMapping("/addperson")
    public PersonaOutPutFather addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        return personaService.addPersona(personaInput);
    }
    @DeleteMapping("/personas/{id}")
    public void deletePersonaById(@PathVariable Long id) {
        personaService.deletePersonaById(id);
    }
    @PutMapping("/personas/{id}")
    public PersonaOutPutSimple updatePersona(@PathVariable Long id, @RequestBody PersonaInput personaInput) {
        return personaService.updatePersona(id, personaInput);
    }
    @GetMapping("/{nombre}")
    public PersonaOutPutSimple getPersonaPorNombre(@PathVariable String nombre) {
            return personaService.getPersonaPorNombre(nombre);
    }

    @GetMapping("profesor/{id}")
    public ProfesorOutputSimple getProfesor(@PathVariable int id) {
        RestTemplate rt = new RestTemplate();
        return rt.getForObject("http://localhost:8081/Profesores/buscar/" + id, ProfesorOutputSimple.class);
    }




}
