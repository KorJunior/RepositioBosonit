package com.example.block7crudvalidation.clase.estudianteAsignatura.controller;

import com.example.block7crudvalidation.clase.estudianteAsignatura.aplicacion.EstudianteAsignaturaServiceImp;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaInput;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutFather;
import com.example.block7crudvalidation.clase.estudianteAsignatura.controller.dto.EstudianteAsignaturaOutPutSimple;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutPutFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Asignaturas")
@RestController
public class ControllerEstudianteAsignatura {

    @Autowired
    private EstudianteAsignaturaServiceImp estudiatnesAsignaturaServiceImp;

    @GetMapping("/asignatura/{id}")
    public EstudianteAsignaturaOutFather buscarAsignatura(@PathVariable("id") Long id, @RequestParam("outputType") String outputType) {
        return estudiatnesAsignaturaServiceImp.buscarAsignatura(id, outputType);
    }

    @GetMapping("/listar")
    public List<EstudianteAsignaturaOutFather> listarPersonas(@RequestParam("outputType") String outputType) {
        List<EstudianteAsignaturaOutFather> profesorOutPutFathers;

        if (outputType.equalsIgnoreCase("full")) {
            profesorOutPutFathers = estudiatnesAsignaturaServiceImp.listarAsignatura(outputType);
            return new ArrayList<>(profesorOutPutFathers);
        } else {
            profesorOutPutFathers = estudiatnesAsignaturaServiceImp.listarAsignatura(outputType);
            return new ArrayList<>(profesorOutPutFathers);
        }
    }

    @PostMapping("/agregar")
    public EstudianteAsignaturaOutPutSimple añadirAlumno(@RequestBody EstudianteAsignaturaInput asignaturaInput) throws Exception {

        return estudiatnesAsignaturaServiceImp.añadirAsignatura(asignaturaInput);
    }
    @DeleteMapping("/asignatura/{id}")
    public void borrarAsignatura(@PathVariable("id") Long id) {
        estudiatnesAsignaturaServiceImp.borrarAsignarura(id);
    }
    @PutMapping("/asignatura/{id}")
    public EstudianteAsignaturaOutPutSimple updateAsignatura(@PathVariable("id") Long id, @RequestBody EstudianteAsignaturaInput asignaturaInput) {
        return estudiatnesAsignaturaServiceImp.updateAsignatura(id, asignaturaInput);
    }





}
