package com.example.block6personcontrollers;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private Servicio servicio;


    public Controlador1(Servicio servicio) {
        this.servicio = servicio;

    }
    @PostMapping("/addPersona")
    public ResponseEntity<String> addPersona(@RequestBody Persona persona) {
        servicio.guardarPersona(persona);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Nombre", persona.getNombre());
        headers.add("Edad", String.valueOf(persona.getEdad()));
        headers.add("Poblacion", persona.getPoblacion());

        return ResponseEntity.ok().headers(headers).body(HttpStatus.OK + ": Mandando los headers");
    }
    @PostMapping("/addCiudad")
    public ResponseEntity<String> addCiudad(@RequestBody Ciudad ciudad) {

        servicio.addCiudad(ciudad);
        return ResponseEntity.ok().body(HttpStatus.OK + "Ciudad Añadida");
    }

}



