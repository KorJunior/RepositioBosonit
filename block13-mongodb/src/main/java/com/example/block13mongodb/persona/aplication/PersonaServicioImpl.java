package com.example.block13mongodb.persona.aplication;


import com.example.block13mongodb.persona.controller.dto.PersonaInput;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutFather;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutFull;
import com.example.block13mongodb.persona.controller.dto.PersonaOutPutSimple;
import com.example.block13mongodb.persona.domain.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonaServicioImpl implements PersonaServicio {
    /*@Autowired
    private PersonaImpRepositorio personaRepository;
*/
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PersonaOutPutFather> listarPersonas(@RequestParam("outputType") String outputType) {
        List<PersonaEntity> listaPersonas = mongoTemplate.findAll(PersonaEntity.class);
        List<PersonaOutPutFather> personaOutPutFathers = new ArrayList<>();

        for (PersonaEntity persona : listaPersonas) {
            if (outputType.equalsIgnoreCase("full")) {
                personaOutPutFathers.add(persona.parsePersonaOutputFull());
            } else {
                personaOutPutFathers.add(persona.parsePersonaOutputSimple());
            }
        }


        return personaOutPutFathers;
    }

    @Override
    public PersonaOutPutSimple addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        PersonaEntity p;
        Optional<PersonaInput> personaOptional = Optional.ofNullable(personaInput);

        if (personaOptional.isPresent()) {
            p = new PersonaEntity(personaInput);
            mongoTemplate.save(p);
            return p.parsePersonaOutputSimple();
        } else {
            throw new Exception("No se ha podido crear la persona");
        }
    }

    @Override
    public void deletePersonaById(String id) {
        Optional<PersonaEntity> op1 = Optional.ofNullable(mongoTemplate.findById(id, PersonaEntity.class));
        PersonaEntity persona;

        if (op1.isPresent()) {
            persona = op1.get();
            mongoTemplate.remove(persona);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PersonaOutPutSimple updatePersona(@PathVariable String id, @RequestBody PersonaInput personaData) {
        Optional<PersonaEntity> personaOptional = Optional.ofNullable(mongoTemplate.findById(id, PersonaEntity.class));
        PersonaEntity persona;

        if (personaOptional.isPresent()) {
            persona = new PersonaEntity(personaData);
            persona.setId(id);
            mongoTemplate.save(persona);
            return persona.parsePersonaOutputSimple();

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PersonaOutPutSimple buscarPersonaID(@PathVariable String id) {
        PersonaEntity personaEntity;
        Optional<PersonaEntity> optionalEntity = Optional.ofNullable(mongoTemplate.findById(id, PersonaEntity.class));

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (optionalEntity.isPresent()) {
            return optionalEntity.get().parsePersonaOutputSimple();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
    public List<String> listarPersonasPagina(String outputType, int pagina, int tamanoPagina) {
        List<String> hoja = crearHoja(mongoTemplate.findAll(PersonaEntity.class), outputType);
        int totalAtributos = hoja.size();
        int totalPaginas = (int) Math.ceil((double) totalAtributos / tamanoPagina);
        int inicio = (pagina - 1) * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, totalAtributos);

        if (pagina <= 0 || pagina > totalPaginas) {
            return new ArrayList<>(); // Página inválida, retornar una lista vacía
        }

        return hoja.subList(inicio, fin);
    }
    private List<String> crearHoja(List<PersonaEntity> personas, String outputType) {
        List<String> hoja = new ArrayList<>();
        PersonaOutPutSimple personaOutPutSimple;
        PersonaOutPutFull personaOutPutFull;

        for (PersonaEntity persona : personas) {

            if ("simple".equals(outputType)) {
                personaOutPutSimple = persona.parsePersonaOutputSimple();
                hoja.add("id: " + personaOutPutSimple.getId());
                hoja.add("usuario: " + personaOutPutSimple.getUsuario());
                hoja.add("name: " + personaOutPutSimple.getName());
                hoja.add("surname: " + personaOutPutSimple.getSurname());
                hoja.add("company_email: " + personaOutPutSimple.getCompany_email());
                hoja.add("personal_email: " + personaOutPutSimple.getPersonal_email());
                hoja.add("city: " + personaOutPutSimple.getCity());
                hoja.add("active: " + personaOutPutSimple.getActive());
                hoja.add("created_date: " + personaOutPutSimple.getCreated_date());
                hoja.add("imagen_url: " + personaOutPutSimple.getImagen_url());
                hoja.add("termination_date: " + personaOutPutSimple.getTermination_date());
            } else {
                personaOutPutFull = persona.parsePersonaOutputFull();
                hoja.add("id: " + personaOutPutFull.getId());
                hoja.add("usuario: " + personaOutPutFull.getUsuario());
                hoja.add("name: " + personaOutPutFull.getName());
                hoja.add("surname: " + personaOutPutFull.getSurname());
                hoja.add("company_email: " + personaOutPutFull.getCompany_email());
                hoja.add("personal_email: " + personaOutPutFull.getPersonal_email());
                hoja.add("city: " + personaOutPutFull.getCity());
                hoja.add("active: " + personaOutPutFull.getActive());
                hoja.add("created_date: " + personaOutPutFull.getCreated_date());
                hoja.add("imagen_url: " + personaOutPutFull.getImagen_url());
                hoja.add("termination_date: " + personaOutPutFull.getTermination_date());

            }
        }

        return hoja;
    }

}
