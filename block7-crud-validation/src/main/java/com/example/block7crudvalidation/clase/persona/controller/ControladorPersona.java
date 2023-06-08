package com.example.block7crudvalidation.clase.persona.controller;

import com.example.block7crudvalidation.clase.persona.aplicacion.PersonaServiceImpl;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFather;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutFull;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPutSimple;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/personas/buscar")
    public List<PersonaOutPutFather> buscarPersonas(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaCreacion,
            @RequestParam(required = false) String ordenarPor
    ) {
        List<PersonaOutPutFather> personas;
        personas = personaService.buscarPersonas(user, name, surname, fechaCreacion, ordenarPor);
        return personas;
    }
    @GetMapping("/personasPaginado")
    public List<String> listarPersonasPagina(
            @RequestParam(required = false, defaultValue = "full") String outputType,
            @RequestParam(required = true, defaultValue = "1") int pagina,
            @RequestParam(required = false, defaultValue = "10") int tamanoPagina
    ) {
        List<String> hojas = personaService.listarPersonasPagina(outputType, pagina, tamanoPagina);
        return hojas;
    }






}
