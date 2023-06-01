package com.example.block7crudvalidation.clase.profesor.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.EstudianteImpRepositorio;
import com.example.block7crudvalidation.clase.estudiante.aplicacion.EstudianteServiceImp;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.estudianteAsignatura.dominio.EstudianteAsignaturaEntity;
import com.example.block7crudvalidation.clase.persona.PersonaImpRepositorio;
import com.example.block7crudvalidation.clase.persona.dominio.PersonaEntity;
import com.example.block7crudvalidation.clase.profesor.ProfesorImpRepositorio;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorInput;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutPutFather;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import com.example.block7crudvalidation.clase.profesor.dominio.ProfesorEntity;
import com.example.block7crudvalidation.excepciones.EntityNotFoundException;
import com.example.block7crudvalidation.excepciones.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfesorServiceImp implements ProfesorService {

    @Autowired
    private ProfesorImpRepositorio profesorRepository;
    @Autowired
    private PersonaImpRepositorio personaImpRepositorio;
    @Autowired
    private EstudianteServiceImp estudianteServiceImp;


    @Override
    public ProfesorOutputSimple buscarProfesor(Long id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con ID: " + id));

        return profesorEntity.parseProfesorOutputSimple();
    }

    @Override
    public List<ProfesorOutPutFather> listarProfesores(String outputType) {
        List<ProfesorEntity> profesorEntities = profesorRepository.findAll();
        List<ProfesorOutPutFather> profesorOutFatherList = new ArrayList<>();
        ProfesorOutPutFather profesorOutFather;

        for (ProfesorEntity profesorEntity : profesorEntities) {
            if (outputType.equalsIgnoreCase("full")) {
                 profesorOutFather = profesorEntity.parseProfesorOutputFull();
                profesorOutFatherList.add(profesorOutFather);
            } else {
                 profesorOutFather = profesorEntity.parseProfesorOutputSimple();
                profesorOutFatherList.add(profesorOutFather);
            }
        }

        return profesorOutFatherList;
    }



    public ProfesorOutputSimple addProfesor(ProfesorInput profesorInput) throws Exception {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        Optional<PersonaEntity> op1 = personaImpRepositorio.findById(profesorInput.getId());

        if (op1.isPresent()) {
            profesorEntity.setBranch(profesorInput.getBranch());
            profesorEntity.setPersonaEntity(op1.get());
            profesorRepository.save(profesorEntity);
        } else {
            throw new UnprocessableEntityException("No existe la persona con id: ");
        }

        return profesorEntity.parseProfesorOutputSimple();
    }

    @Override
    public void deletePersonaById(Long id) {
        Optional<ProfesorEntity> op1 = profesorRepository.findById(id);
        ProfesorEntity profesorEntity;
        PersonaEntity personaEntity;
        List<EstudianteEntity> estudiantes;

        if (op1.isPresent()) {
            profesorEntity = op1.get();
            estudiantes = profesorEntity.getEstudianteEntities();
            if (estudiantes != null) {
                for (EstudianteEntity estudianteEntity : estudiantes) {
                    estudianteServiceImp.borrarEstudiante(estudianteEntity.getId_estudiante());
                    if (estudiantes.size() == (0)) {
                        break;
                    }
                }
            }
            profesorEntity.setEstudianteEntities(null);
            personaEntity = profesorEntity.getPersonaEntity();
            profesorEntity.setPersonaEntity(null);


        } else {
            throw new EntityNotFoundException("Profesor no encontrado con ID: " + id);
        }
        personaImpRepositorio.save(personaEntity);
        profesorRepository.save(profesorEntity);
        profesorRepository.deleteById(id);
    }

    @Override
    public ProfesorOutputSimple updatePersona(Long id, ProfesorInput profesorInput) {
        Optional<ProfesorEntity> optionalProfesorEntity = profesorRepository.findById(id);
        ProfesorEntity existingProfesorEntity;

        if (optionalProfesorEntity.isPresent()) {
            existingProfesorEntity = optionalProfesorEntity.get();
            existingProfesorEntity.setBranch(profesorInput.getBranch());
            profesorRepository.save(existingProfesorEntity);

            return existingProfesorEntity.parseProfesorOutputSimple();
        } else {
            throw new EntityNotFoundException("Profesor no encontrado con ID: " + id);
        }
    }
}



