package com.example.block5commandlinerunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerceraClase implements CommandLineRunner {

    @Value("${propiedad1}")
    private String valorPropiedad1;

    @Value("${propiedad2}")
    private String valorPropiedad2;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Soy la tercera clase");
        System.out.println("Valores de las propiedades:");

        System.out.println("Propiedad1: " + valorPropiedad1);
        System.out.println("Propiedad2: " + valorPropiedad2);
    }
}
