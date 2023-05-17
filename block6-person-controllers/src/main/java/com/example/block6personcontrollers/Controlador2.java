package com.example.block6personcontrollers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador1")
public class Controlador2 {
    private Servicio servicio;

       public Controlador2(Servicio servicio) {
            this.servicio = servicio;
        }

    @GetMapping("/getPersona")
    public Persona getPersonaMultiplicada() {
        Persona persona = servicio.getPersonaGuardada();

        if (persona != null) {
            persona.setEdad(persona.getEdad() * 2);
        }

        return persona;
    }
    @GetMapping("/getCiudades")
    public ArrayList<Ciudad> getCiudades() {

        return servicio.getCiudades();
    }

}
