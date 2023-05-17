package com.example.block6personcontrollers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class Servicio implements CommandLineRunner {

    private Persona personaGuardada;
    private ArrayList<Ciudad> ciudades = new ArrayList<>();

    public Servicio(Persona personaGuardada, ArrayList<Ciudad> ciudades) {
        this.personaGuardada = personaGuardada;
        this.ciudades = ciudades;
    }
    public Servicio(Persona personaGuardada) {
        this(personaGuardada,null);;
    }
    public Servicio(ArrayList<Ciudad> ciudades) {
        this(null,ciudades);;
    }
    public Servicio() {
    }

    public void guardarPersona(Persona persona) {
        personaGuardada = persona;
    }

    // Otros métodos del servicio

    public Persona getPersonaGuardada() {
        return personaGuardada;
    }

    @Override
    public void run(String... args) throws Exception {
        ciudades = new ArrayList<Ciudad>();
    }

    public void setPersonaGuardada(Persona personaGuardada) {
        this.personaGuardada = personaGuardada;
    }


    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    public void addCiudad(Ciudad ciudad){
        ciudades.add(ciudad);
    }
}
