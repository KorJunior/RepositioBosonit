package com.example.block7crudvalidation.clase.estudiante.controller;

import com.example.block7crudvalidation.clase.estudiante.aplicacion.EstudianteServiceImp;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteInPut;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutFather;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutPutSimple;
import com.example.block7crudvalidation.clase.estudiante.controller.dto.EstudianteOutputFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Estudiantes")
@RestController
public class ControllerEstudiante {
    @Autowired
    private EstudianteServiceImp estudianteServiceImp;

    @GetMapping("/listar")
    public List<EstudianteOutFather> listarPersonas(@RequestParam("outputType") String outputType) {
        if (outputType.equalsIgnoreCase("full")) {
            List<EstudianteOutFather> estudianteOutputFulls = estudianteServiceImp.listarEstudiante(outputType);
            return new ArrayList<>(estudianteOutputFulls);
        } else {
            List<EstudianteOutFather> estudianteOutPutSimples = estudianteServiceImp.listarEstudiante(outputType);
            return new ArrayList<>(estudianteOutPutSimples);
        }
    }
    @PostMapping("/estudiantes/{id}")
    public void asignarAsignaturas(@PathVariable("id") Long id,@RequestBody List<Long> listaId ) {
        estudianteServiceImp.asignarAsignaturas(id, listaId);
    }
    @DeleteMapping("/estudiantes/{id}")
    public void desasignarAsignaturas(@PathVariable Long estudianteId,@RequestBody List<Long> asignaturaIds) {
            estudianteServiceImp.desasignarAsignaturas(estudianteId, asignaturaIds);


    }

    @GetMapping("/estudiante/{id}")
    public EstudianteOutFather buscarEstudiante(@PathVariable("id") Long id, @RequestParam("outputType") String outputType) {
        return estudianteServiceImp.buscarEstudiante(id, outputType);
    }


    @PostMapping("/agregar")
    public EstudianteOutputFull añadirAlumno(@RequestBody EstudianteInPut estudiante) throws Exception {

        return estudianteServiceImp.addEstudiante(estudiante);

    }

    @PutMapping("/estudiante/{id}")
    public EstudianteOutputFull updatePersona(@PathVariable("id") Long id, @RequestBody EstudianteInPut persona) {
        return estudianteServiceImp.updatePersona(id, persona);
    }

    @DeleteMapping("/estudiante/{id}")
    public void borrarEstudiante(@PathVariable("id") Long id) {
        estudianteServiceImp.borrarEstudiante(id);
    }

}
