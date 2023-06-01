package com.example.block7crudvalidation.clase.persona.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.aplicacion.EstudianteServiceImp;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.persona.PersonaImpRepositorio;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaInput;
import com.example.block7crudvalidation.clase.persona.controller.dto.Persona.PersonaOutPut;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.aplicacion.ProfesorServiceImp;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import com.example.block7crudvalidation.excepciones.EntityNotFoundException;
import com.example.block7crudvalidation.excepciones.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaImpRepositorio personaRepository;
    @Autowired
    private ProfesorServiceImp profesorServiceImp;
    @Autowired
    private EstudianteServiceImp estudianteServiceImp;

    @Override
    @GetMapping("/personaid/{id}")
    public PersonaOutPut buscarPersonaID(@PathVariable Long id) {
        PersonaEntity personaEntity;
        Optional<PersonaEntity> optionalEntity = personaRepository.findById(id);

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (optionalEntity.isPresent()) {
            return optionalEntity.get().parsePersonaOutputDTO();
        } else {
            throw new EntityNotFoundException("No se ha encontrado la persona con el id: " + id);
        }

    }


    @Override
    @GetMapping("/mostrar")
    public List<PersonaOutPut> listarPersonas() {
        List<PersonaEntity> listaPersonas = personaRepository.findAll();
        List<PersonaOutPut> personaOutputs = new ArrayList<>();
        ;

        for (PersonaEntity persona : listaPersonas) {
            personaOutputs.add(persona.parsePersonaOutputDTO());
        }
        return personaOutputs;
    }

    @Override
    @PostMapping("/persona")
    public PersonaOutPut addPersona(@RequestBody PersonaInput personaInput) throws Exception {
        PersonaEntity p;
        Optional<PersonaInput> personaOptional = Optional.ofNullable(personaInput);

        if (personaOptional.isPresent()) {
            if (personaInput.getUsuario().length() >= 10 && personaInput.getUsuario().length() <= 6) {
                throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
            } else if (personaInput.getPassword() == null) {
                throw new UnprocessableEntityException("Longitud de password no puede ser inferior a 8 caracteres");
            } else if (personaInput.getCompany_email() == null) {
                throw new UnprocessableEntityException("El email de la empresa no puede contener el nombre de usuario");
            } else if (personaInput.getPersonal_email() == null) {
                throw new UnprocessableEntityException("El email personal no puede contener el nombre de usuario");
            } else if (personaInput.getPersonal_email().contains(personaInput.getCompany_email())) {
                throw new UnprocessableEntityException("El email personal no puede contener el email de la empresa");
            } else if (personaInput.getCity() == null) {
                throw new UnprocessableEntityException("Longitud de ciudad no puede ser inferior a 3 caracteres");
            } else if (personaInput.getActive() == null) {
                throw new UnprocessableEntityException("El campo active no puede ser nulo");
            } else {
                p = new PersonaEntity(personaInput);
                personaRepository.save(p);
                return p.parsePersonaOutputDTO();
            }
        } else {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        }
    }

    @Override
    public void deletePersonaById(Long id) {
        Optional<PersonaEntity> op1 = personaRepository.findById(id);
        PersonaEntity persona;
        ProfesorEntity profesorEntity;
        EstudianteEntity estudianteEntity;

        if (op1.isPresent()){
            persona = op1.get();
            profesorEntity = persona.getProfesorEntity();
            estudianteEntity = persona.getEstudianteEntity();

            if (profesorEntity != null){
                profesorServiceImp.deletePersonaById(profesorEntity.getId_profesor());
            } else if (estudianteEntity != null){
                estudianteServiceImp.borrarEstudiante(estudianteEntity.getId_estudiante());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        persona.setEstudianteEntity(null);
        persona.setProfesorEntity(null);
        personaRepository.save(persona);
        personaRepository.delete(persona);
    }

    @Override
    public PersonaOutPut updatePersona(@PathVariable Long id, @RequestBody PersonaInput personaData) {
        Optional<PersonaEntity> personaOptional = personaRepository.findById(id);
        PersonaEntity persona;

        if (personaOptional.isPresent()) {
            persona = new PersonaEntity(personaData);
            persona.setId(id);
            personaRepository.save(persona);
            return persona.parsePersonaOutputDTO();

        } else {
            throw new UnprocessableEntityException("El usuario que esta buscando para la actualizacion no existe");
        }
    }

    public PersonaOutPut getPersonaPorNombre(@PathVariable String nombre) {
        List<PersonaEntity> listaPersonas = personaRepository.findAll();
        PersonaOutPut p = null;

        for (PersonaEntity persona : listaPersonas) {
            if (persona.getName().equals(nombre)) {
                return persona.parsePersonaOutputDTO();
            }
        }
        throw new UnprocessableEntityException("No existe ningun usuario con ese nombre");
    }
}
