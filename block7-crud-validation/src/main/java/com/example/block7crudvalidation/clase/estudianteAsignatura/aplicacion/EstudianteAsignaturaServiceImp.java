package com.example.block7crudvalidation.clase.estudianteAsignatura.aplicacion;

import com.example.block7crudvalidation.clase.estudiante.EstudianteImpRepositorio;
import com.example.block7crudvalidation.clase.estudiante.dominio.EstudianteEntity;
import com.example.block7crudvalidation.clase.estudianteAsignatura.EstudianteAsignaturaRepositorio;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaInput;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutFather;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import com.example.block7crudvalidation.clase.estudianteAsignatura.dominio.EstudianteAsignaturaEntity;
import com.example.block7crudvalidation.excepciones.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EstudianteAsignaturaServiceImp implements EstudianteAsignaturaService {

    @Autowired
    private EstudianteImpRepositorio estudianteImpRepositorio;
    @Autowired
    private EstudianteAsignaturaRepositorio estudianteAsignaturaRepositorio;

    @Override
    public EstudianteAsignaturaOutFather buscarAsignatura(Long id, String outputType) {
        Optional<EstudianteAsignaturaEntity> op1 = estudianteAsignaturaRepositorio.findById(id);
        EstudianteAsignaturaEntity asignatura;
        EstudianteAsignaturaOutFather asignaturaOutFather;

        if (op1.isPresent()) {
            asignatura = op1.get();
            if (outputType.equalsIgnoreCase("full")) {
                asignaturaOutFather = asignatura.parseAsignaturaOutputFull();
                return asignaturaOutFather;
            } else {
                asignaturaOutFather = asignatura.parseAsignaturaOutputSimple();
                return asignaturaOutFather;
            }
        } else {
            throw new UnprocessableEntityException("No existe la asignatura con id: " + id);
        }
    }

    @Override
    public List<EstudianteAsignaturaOutFather> listarAsignatura(String outputType) {
        List<EstudianteAsignaturaEntity> estudianteAsignaturaEntities = estudianteAsignaturaRepositorio.findAll();
        List<EstudianteAsignaturaOutFather> asignaturaOutFatherList = new ArrayList<>();

        for (EstudianteAsignaturaEntity asignatura : estudianteAsignaturaEntities) {
            if (outputType.equalsIgnoreCase("full")) {
                EstudianteAsignaturaOutFather asignaturaOutFather = asignatura.parseAsignaturaOutputFull();
                asignaturaOutFatherList.add(asignaturaOutFather);
            } else {
                EstudianteAsignaturaOutFather asignaturaOutFather = asignatura.parseAsignaturaOutputSimple();
                asignaturaOutFatherList.add(asignaturaOutFather);
            }
        }

        return asignaturaOutFatherList;
    }


    @Override
    public EstudianteAsignaturaOutPutSimple añadirAsignatura(EstudianteAsignaturaInput estudianteAsignaturaInput) throws Exception {
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        EstudianteAsignaturaEntity asignatura = new EstudianteAsignaturaEntity(estudianteAsignaturaInput);
        List<EstudianteAsignaturaEntity> estudianteAsignaturaEntities = new ArrayList<>();
        List<EstudianteEntity> estudiantes = new ArrayList<>();
        Optional<EstudianteEntity> op1 = estudianteImpRepositorio.findById(estudianteAsignaturaInput.getId_estudiante());

        if (op1.isPresent()) {
            estudianteEntity = op1.get();
            estudianteAsignaturaEntities = estudianteEntity.getEstudios();
            estudianteAsignaturaEntities.add(asignatura);
            estudianteEntity.setEstudios(estudianteAsignaturaEntities);

            estudiantes.add(estudianteEntity);
            asignatura.setEstudiantesEntities(estudiantes);
            estudianteAsignaturaRepositorio.save(asignatura);
            estudianteImpRepositorio.save(estudianteEntity);


        } else {
            throw new UnprocessableEntityException("No existe la persona con id: ");
        }

        return asignatura.parseAsignaturaOutputSimple();
    }

    @Override
    public void borrarAsignarura(Long id) {
        Optional<EstudianteAsignaturaEntity> op1 = estudianteAsignaturaRepositorio.findById(id);
        EstudianteAsignaturaEntity estudianteAsignaturaEntity;
        List<EstudianteEntity> esA1;
        List<EstudianteAsignaturaEntity> asignaturas;

        if (op1.isPresent()){
            estudianteAsignaturaEntity= op1.get();


            esA1 = estudianteAsignaturaEntity.getEstudiantesEntities();

            for (EstudianteEntity esA : esA1) {
                asignaturas = esA.getEstudios();
                if (asignaturas != null) {
                    asignaturas.removeIf(asignatura -> asignatura.equals(estudianteAsignaturaEntity));
                    estudianteImpRepositorio.save(esA);
                }
            }

        }else{
            throw new UnprocessableEntityException("No existe la asignatura con id: " + id);
        }
        estudianteAsignaturaEntity.setEstudiantesEntities(null);
        estudianteAsignaturaRepositorio.save(estudianteAsignaturaEntity);
        estudianteAsignaturaRepositorio.deleteById(id);

    }

    @Override
    public EstudianteAsignaturaOutPutSimple updateAsignatura(Long id, EstudianteAsignaturaInput asignaturaInput) {
        Optional<EstudianteAsignaturaEntity> op1 = estudianteAsignaturaRepositorio.findById(id);
        Optional<EstudianteEntity> op2 = estudianteImpRepositorio.findById(asignaturaInput.getId_estudiante());
        EstudianteAsignaturaEntity asignaturaEntity;
        List<EstudianteAsignaturaEntity> estudianteAsignaturaEntities = new ArrayList<>();
        EstudianteEntity estudianteEntity;
        List<EstudianteEntity> estudiantes = new ArrayList<>();

        if (op1.isPresent()&&op2.isPresent()) {
            asignaturaEntity = op1.get();
            estudianteEntity = op2.get();

            estudianteAsignaturaEntities = estudianteEntity.getEstudios();
            estudiantes = asignaturaEntity.getEstudiantesEntities();
            asignaturaEntity.setAsignatura(asignaturaInput.getAsignatura());
            asignaturaEntity.setComents(asignaturaInput.getComents());
            asignaturaEntity.setInitial_date(asignaturaInput.getInitial_date());
            asignaturaEntity.setFinish_date(asignaturaInput.getFinish_date());

            estudianteAsignaturaEntities.add(asignaturaEntity);
            estudianteEntity.setEstudios(estudianteAsignaturaEntities);
            estudiantes.add(estudianteEntity);
            asignaturaEntity.setEstudiantesEntities(estudiantes);
            estudianteImpRepositorio.save(estudianteEntity);
            estudianteAsignaturaRepositorio.save(asignaturaEntity);
            return asignaturaEntity.parseAsignaturaOutputSimple();
        } else {
            throw new UnprocessableEntityException("No existe la asignatura con id: " + id);
        }

    }
}
