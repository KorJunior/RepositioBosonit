package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InvalidLineFormatException {
        String nombreFichero = "people.csv";


        leerListas(filtradoPersonasMenores25(leerFichero(nombreFichero)));
        //leerListas(filtrarPersonasConA(leerFichero(nombreFichero)));
        //filtrarPrimeroMadrid(filtradoPersonasMenores25(((leerFichero(nombreFichero)))));
        //filtrarPrimeroBarcelona(filtradoPersonasMenores25(leerFichero(nombreFichero)));
    }

    private static void leerListas(List<Person> lista) {

        for (Person elemento : lista) {
            System.out.println(elemento);
        }
    }

    private static void filtrarPrimeroMadrid(List<Person> listaPersonas) {
        Optional<Person> personaEncontrada;

        personaEncontrada = listaPersonas.stream().filter(persona -> persona.getTown().equalsIgnoreCase("Madrid")).findFirst();

        if (personaEncontrada.isPresent()) {
            System.out.println(personaEncontrada.get());

        } else {
            System.out.println("No hay Registros");
        }


    }

    private static void filtrarPrimeroBarcelona(List<Person> listaPersonas) {
        Optional<Person> personaEncontrada;

        personaEncontrada = listaPersonas.stream().filter(persona -> persona.getTown().equalsIgnoreCase("Barcelona")).findFirst();

        if (personaEncontrada.isPresent()) {
            System.out.println(personaEncontrada.get());

        } else {
            System.out.println("No hay Registros");
        }


    }

    private static List<Person> filtrarPersonasConA(List<Person> listaPersonas) {
        List<Person> personas;
        Pattern pattern = Pattern.compile("^[aAáÁ].*");

        return listaPersonas.stream().filter(persona -> !pattern.matcher(persona.getName()).find()).collect(Collectors.toList());

    }

    private static List<Person> filtradoPersonasMenores25(ArrayList<Person> listaPersonas) {
        List<Person> personas;

        return listaPersonas.stream().filter(persona -> persona.getAge() > 0 && persona.getAge() < 25).collect(Collectors.toList());
    }

    private static ArrayList<Person> leerFichero(String nombreFichero) throws IOException, InvalidLineFormatException {
        FileReader csv = new FileReader(nombreFichero);
        BufferedReader br = new BufferedReader(csv);
        String lineas="";
        Person personas;
        ArrayList<Person> listaPersonas = new ArrayList<Person>();
        String[] palabras;

            while ((lineas = br.readLine()) != null) {

                // Dividir la línea en palabras utilizando el carácter ":"
                palabras = lineas.split(":");
                if (palabras[0].equalsIgnoreCase("")) {
                    continue;
                } else if (palabras.length == 1) {
                    if (!palabras[0].matches("[\\p{L}\\p{M}\\s]+")) {
                        throw new InvalidLineFormatException("La línea contiene caracteres no válidos: " + lineas);
                    } else {
                        listaPersonas.add(personas = new Person(palabras[0], "unknown", (byte) 0));
                    }
                } else if (palabras[1].equalsIgnoreCase("")) {
                    if (!palabras[0].matches("[\\p{L}\\p{M}\\s]+") || !palabras[2].matches("\\d+") ) {
                        throw new InvalidLineFormatException("La línea contiene caracteres no válidos: " + lineas);
                    } else {
                        listaPersonas.add(personas = new Person(palabras[0], "unknown", Byte.parseByte(palabras[2])));
                    }
                } else if (palabras.length == 2) {
                    if (!palabras[0].matches("[\\p{L}\\p{M}\\s]+") ||!palabras[1].matches("[\\p{L}\\p{M}\\s]+") ){
                        throw new InvalidLineFormatException("La línea contiene caracteres no válidos: " + lineas);
                    }else{
                        listaPersonas.add(personas = new Person(palabras[0], palabras[1], (byte) 0));
                    }
                } else {
                    if (!palabras[0].matches("[\\p{L}\\p{M}\\s]+") || !palabras[1].matches("[\\p{L}\\p{M}\\s]+") || !palabras[2].matches("\\d+")) {
                        throw new InvalidLineFormatException("La línea contiene caracteres no válidos: " + lineas);
                    } else {
                        listaPersonas.add(personas = new Person(palabras[0], palabras[1], Byte.parseByte(palabras[2])));
                    }
                }



        }
        return listaPersonas;
    }
}