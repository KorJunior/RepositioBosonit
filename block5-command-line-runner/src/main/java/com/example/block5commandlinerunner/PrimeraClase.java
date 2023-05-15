package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PrimeraClase {
    @PostConstruct
    public void metodoInicial() {
        System.out.println("Hola desde clase inicial");
    }
}
