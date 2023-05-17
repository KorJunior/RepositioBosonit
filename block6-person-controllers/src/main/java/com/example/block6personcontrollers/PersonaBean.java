package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersonaBean {
    @Bean
    @Qualifier("bean1")
    public Persona obtenerNombre1() {
        return new Persona("Bean1", 10, "Madrid");
    }

    @Bean
    @Qualifier("bean2")
    public Persona obtenerNombre2() {
        return new Persona("Bean2", 20, "Barcelona");
    }

    @Bean
    @Qualifier("bean3")
    public Persona obtenerNombre3() {
        return new Persona("Bean3", 30, "Sevilla");
    }
}
