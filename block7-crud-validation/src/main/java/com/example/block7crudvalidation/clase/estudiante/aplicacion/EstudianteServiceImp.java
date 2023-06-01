package com.example.block7crudvalidation.clase.estudiante.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.EstudianteImpRepositorio;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteInPut;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutFather;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutputFull;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.estudianteAsignatura.EstudianteAsignaturaRepositorio;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutFather;
import com.example.block7crudvalidation.clase.estudianteAsignatura.dominio.EstudianteAsignaturaEntity;
import com.example.block7crudvalidation.clase.persona.PersonaImpRepositorio;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.ProfesorImpRepositorio;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import com.example.block7crudvalidation.excepciones.EntityNotFoundException;
import com.example.block7crudvalidation.excepciones.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EstudianteServiceImp implements EstudianteService {

    @Autowired
    private ProfesorImpRepositorio profesorRepository;
    @Autowired
    private PersonaImpRepositorio personaImpRepositorio;
    @Autowired
    private EstudianteImpRepositorio estudianteImpRepositorio;
    @Autowired
    private EstudianteAsignaturaRepositorio estudianteAsignaturaRepositorio;

    @Override
    public EstudianteOutFather buscarEstudiante(Long id,String outputType) {
        Optional <EstudianteEntity> op1 = estudianteImpRepositorio.findById(id);
        EstudianteEntity estudianteEntity;
        EstudianteOutFather estudianteOutFather;


        if (op1.isPresent()) {
            estudianteEntity = op1.get();
            if (outputType.equalsIgnoreCase("full")) {
                estudianteOutFather = estudianteEntity.parseEstudianteOutputFull();
                return estudianteOutFather;
            } else {
                estudianteOutFather = estudianteEntity.parseEstudianteOutputSimple();
                return estudianteOutFather;
            }
        }else{
            throw new UnprocessableEntityException("No existe la asignatura con id: " + id);
        }
    }

    @Override
    public List<EstudianteOutFather> listarEstudiante(String outputType) {
        List<EstudianteEntity> estudiantes = estudianteImpRepositorio.findAll();
        List<EstudianteOutFather> estudianteOutFather = new ArrayList<>();

        for (EstudianteEntity estudiante : estudiantes) {
            if (outputType.equalsIgnoreCase("full")) {
                estudianteOutFather.add(estudiante.parseEstudianteOutputFull());
            } else {
                EstudianteOutPutSimple estudianteOutPutSimple = estudiante.parseEstudianteOutputSimple();
                estudianteOutFather.add(estudianteOutPutSimple);
            }
        }

        return estudianteOutFather;
    }



    @Override
    public EstudianteOutputFull addEstudiante(EstudianteInPut estudianteInPut) throws Exception {
        EstudianteEntity estudiante = new EstudianteEntity();
        ProfesorEntity profesorEntity;

        Optional<PersonaEntity> op1 = personaImpRepositorio.findById(estudianteInPut.getId_persona());
        Optional<ProfesorEntity> op2 = profesorRepository.findById(estudianteInPut.getId_profesor());
        List<EstudianteEntity> estudianteEntities = new ArrayList<>();

        if (op1.isPresent() && op2.isPresent()) {
            estudiante.setComents(estudianteInPut.getComents());
            estudiante.setPersonaEntity(op1.get());
            estudiante.setNum_hours_week(estudianteInPut.getNum_hours_week());
            estudiante.setProfesorEntity(op2.get());

            profesorEntity = op2.get();
            estudianteEntities = profesorEntity.getEstudianteEntities();
            estudianteEntities.add(estudiante);
            profesorEntity.setEstudianteEntities(estudianteEntities);

            profesorRepository.save(profesorEntity);
            estudianteImpRepositorio.save(estudiante);
        } else {
            throw new UnprocessableEntityException("No existe la persona con id: ");
        }
        return estudiante.parseEstudianteOutputFull();
    }


    @Override
    public void borrarEstudiante(Long id) {
        Optional<EstudianteEntity> op1 = estudianteImpRepositorio.findById(id);
        EstudianteEntity estudianteEntity;
        PersonaEntity personaEntity;
        ProfesorEntity profesorEntity;
        List<EstudianteAsignaturaEntity> esA1;
        List<EstudianteEntity> estudiantes;


        if (op1.isPresent()){
            estudianteEntity= op1.get();
            personaEntity=estudianteEntity.getPersonaEntity();
            personaEntity.setEstudianteEntity(null);

            profesorEntity = estudianteEntity.getProfesorEntity();
            profesorEntity.getEstudianteEntities().remove(estudianteEntity);

            esA1 = estudianteEntity.getEstudios();


            for (EstudianteAsignaturaEntity esA : esA1) {
                estudiantes = esA.getEstudiantesEntities();
                if (estudiantes != null) {
                    estudiantes.removeIf(estudiante -> estudiante.equals(estudianteEntity));
                    estudianteAsignaturaRepositorio.save(esA);
                }
            }

            estudianteEntity.setPersonaEntity(null);
            estudianteEntity.setEstudios(null);
            estudianteEntity.setProfesorEntity(null);


        }else{
            throw new EntityNotFoundException("Estudiante no encontrado con ID: " + id);
        }

        profesorRepository.save(profesorEntity);
        personaImpRepositorio.save(personaEntity);
        estudianteImpRepositorio.save(estudianteEntity);
        estudianteImpRepositorio.deleteById(id);

    }

    @Override
    public EstudianteOutputFull updatePersona(Long id, EstudianteInPut persona) {
        Optional<EstudianteEntity> op1 = estudianteImpRepositorio.findById(id);

        EstudianteEntity estudianteEntity;

        if (op1.isPresent()) {
            estudianteEntity = op1.get();
            estudianteEntity.setNum_hours_week(persona.getNum_hours_week());
            estudianteEntity.setComents(persona.getComents());
            estudianteImpRepositorio.save(estudianteEntity);

            return estudianteEntity.parseEstudianteOutputFull();
        } else {
            throw new EntityNotFoundException("Profesor no encontrado con ID: " + id);
        }
    }

}
