package com.example.block6personcontrollers;

public class Persona {
    private String nombre;
    private int edad;
    private String poblacion;

    public Persona(String nombre, int edad, String poblacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.poblacion = poblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        t.append("Persona : "+ nombre);
        t.append("Edad : "+ edad);
        t.append("Poblacion : "+ poblacion);
        return t.toString();
    }
}
