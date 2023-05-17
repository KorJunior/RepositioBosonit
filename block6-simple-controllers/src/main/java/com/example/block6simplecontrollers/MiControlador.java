package com.example.block6simplecontrollers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MiControlador {
    @PostMapping("/useradd")
    public ResponseEntity<String> addUser(@RequestBody Usuario user) {

        System.out.println("Nuevo usuario recibido: " + user.getNombre());
        return new ResponseEntity<>("Usuario agregado exitosamente", HttpStatus.OK);
    }
    @PostMapping("/addPersona")
    public Persona addPersona(@RequestBody Persona persona) {
        Persona p = new Persona(persona.getNombre(), persona.getEdad()+1, persona.getCiudad());

        return p;
    }



}
