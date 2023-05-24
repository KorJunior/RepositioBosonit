package com.example.block7crudvalidation;

import com.example.block7crudvalidation.Clases.Persona;
import com.example.block7crudvalidation.Excepciones.EntityNotFoundException;
import com.example.block7crudvalidation.Excepciones.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ControladorPersona {

    @Autowired
    private PersonaRepositorio personaRepository;

    @GetMapping("/personaid/{id}")
    public Persona buscarPersonaID (@PathVariable Long id) {
        Persona persona;

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("No se ha encontrado la persona con el id: " + id));
        return persona;

    }

    @GetMapping("/personan/{nombre}")
    @ResponseBody
    public ResponseEntity<Object> getNombre(@PathVariable String nombre) {

        Persona p = null;

        for (Persona persona : personaRepository.findAll()) {
            if (persona.getName().equals(nombre)) {
                p = persona;
            }
        }

        return ResponseEntity.ok(p);
    }

    @GetMapping("/persona/mostrar")
    @ResponseBody
    public List<Persona> listPersonas() {
        return personaRepository.findAll();
    }

    @PostMapping("/persona/persona")
    @ResponseBody
    public Persona addPersona(@RequestBody Persona persona) throws Exception {
        Optional<Persona> personaOptional = Optional.ofNullable(persona);
        if (personaOptional.isPresent()) {
            if (persona.getUsuario().length() >= 10 && persona.getUsuario().length() <= 6) {
                throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
            } else if (persona.getPassword() == null) {
                throw new UnprocessableEntityException ("Longitud de password no puede ser inferior a 8 caracteres");
            } else if (persona.getCompany_email() == null) {
                throw new UnprocessableEntityException ("El email de la empresa no puede contener el nombre de usuario");
            } else if (persona.getPersonal_email() == null) {
                throw new UnprocessableEntityException ("El email personal no puede contener el nombre de usuario");
            } else if (persona.getPersonal_email().contains(persona.getCompany_email())) {
                throw new UnprocessableEntityException ("El email personal no puede contener el email de la empresa");
            } else if (persona.getCity() == null) {
                throw new UnprocessableEntityException ("Longitud de ciudad no puede ser inferior a 3 caracteres");
            } else if (persona.getActive() == null) {
                throw new UnprocessableEntityException ("El campo active no puede ser nulo");
            } else if (persona.getCreated_date() == null) {
                throw new UnprocessableEntityException ("El campo created_date no puede ser nulo");
            } else {
                return personaRepository.save(persona);
            }
        } else {
            throw new UnprocessableEntityException ("Usuario no puede ser nulo");
        }
    }
}
