package com.example.block13mongodb.persona.controller;

import com.example.block13mongodb.persona.aplication.PersonaServicioImpl;
import com.example.block13mongodb.persona.controller.dto.PersonaInput;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutFather;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {
    @Autowired
    private PersonaServicioImpl personaService;

    @GetMapping("/personaid/{id}")
    public PersonaOutPutSimple buscarPersonaID(@PathVariable String id) {
        return personaService.buscarPersonaID(id);
    }
    @GetMapping("/personas")
    public List<PersonaOutPutFather> listarPersonasPagina(@RequestParam(required = false, defaultValue = "full") String outputType) {
        return personaService.listarPersonas(outputType);
    }
    @PostMapping("/addperson")
    public PersonaOutPutFather addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        return personaService.addPersona(personaInput);
    }
    @DeleteMapping("/personas/{id}")
    public void deletePersonaById(@PathVariable String id) {
        personaService.deletePersonaById(id);
    }
    @PutMapping("/personas/{id}")
    public PersonaOutPutSimple updatePersona(@PathVariable String id, @RequestBody PersonaInput personaInput) {
        return personaService.updatePersona(id, personaInput);
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
