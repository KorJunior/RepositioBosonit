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
    public EstudianteOutFather buscarEstudiante(Long id, String outputType) {
        Optional<EstudianteEntity> op1 = estudianteImpRepositorio.findById(id);
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
        } else {
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


        if (op1.isPresent()) {
            estudianteEntity = op1.get();
            personaEntity = estudianteEntity.getPersonaEntity();
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


        } else {
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

    public void asignarAsignaturas(Long estudianteId, List<Long> asignaturaIds) {
        Optional<List<Long>> op1 = Optional.ofNullable(asignaturaIds);
        Optional<EstudianteEntity> op3 = estudianteImpRepositorio.findById(estudianteId);
        Optional<EstudianteAsignaturaEntity> op2;
        List<EstudianteAsignaturaEntity> estudianteAsignaturaEntities;
        List<EstudianteEntity> estudianteEntities ;
        EstudianteEntity estudianteEntity;
        EstudianteAsignaturaEntity estudianteAsignaturaEntity;
        List<Long> asignaturasIds = new ArrayList<>();

        if (op1.isPresent() && op3.isPresent()) {
            estudianteEntity = op3.get();

            for (Long asignaturaId : asignaturaIds) {
                op2 = estudianteAsignaturaRepositorio.findById(asignaturaId);
                if (op2.isPresent()) {
                    estudianteAsignaturaEntity = op2.get();

                    estudianteAsignaturaEntities = new ArrayList<>(estudianteEntity.getEstudios());
                    estudianteAsignaturaEntities.add(estudianteAsignaturaEntity);
                    estudianteEntity.setEstudios(estudianteAsignaturaEntities);

                    estudianteEntities = new ArrayList<>(estudianteAsignaturaEntity.getEstudiantesEntities());
                    estudianteEntities.add(estudianteEntity);
                    estudianteAsignaturaEntity.setEstudiantesEntities(estudianteEntities);

                    estudianteImpRepositorio.save(estudianteEntity);
                    estudianteAsignaturaRepositorio.save(estudianteAsignaturaEntity);
                } else {
                    throw new EntityNotFoundException("No existe la asignatura con id: " + asignaturaId);
                }
            }
        } else {
            throw new EntityNotFoundException("No existe el estudiante con id: " + estudianteId);
        }

    }
    public void desasignarAsignaturas(Long estudianteId, List<Long> asignaturaIds) {
        Optional<List<Long>> op1 = Optional.ofNullable(asignaturaIds);
        Optional<EstudianteEntity> op3 = estudianteImpRepositorio.findById(estudianteId);
        Optional<EstudianteAsignaturaEntity> op2;
        List<EstudianteAsignaturaEntity> estudianteAsignaturaEntities;
        List<EstudianteEntity> estudianteEntities;
        EstudianteEntity estudianteEntity;
        EstudianteAsignaturaEntity estudianteAsignaturaEntity;
        List<Long> asignaturasIds = new ArrayList<>();

        if (op1.isPresent() && op3.isPresent()) {
            estudianteEntity = op3.get();

            for (Long asignaturaId : asignaturaIds) {
                op2 = estudianteAsignaturaRepositorio.findById(asignaturaId);
                if (op2.isPresent()) {
                    estudianteAsignaturaEntity = op2.get();

                    estudianteAsignaturaEntities = new ArrayList<>(estudianteEntity.getEstudios());
                    estudianteAsignaturaEntities.remove(estudianteAsignaturaEntity);
                    estudianteEntity.setEstudios(estudianteAsignaturaEntities);

                    estudianteEntities = new ArrayList<>(estudianteAsignaturaEntity.getEstudiantesEntities());
                    estudianteEntities.remove(estudianteEntity);
                    estudianteAsignaturaEntity.setEstudiantesEntities(estudianteEntities);

                    estudianteImpRepositorio.save(estudianteEntity);
                    estudianteAsignaturaRepositorio.save(estudianteAsignaturaEntity);
                } else {
                    throw new EntityNotFoundException("No existe la asignatura con id: " + asignaturaId);
                }
            }
        } else {
            throw new EntityNotFoundException("No existe el estudiante con id: " + estudianteId);
        }
    }

}
