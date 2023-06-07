package com.example.block7crudvalidation.clase.profesor.controller;

import com.example.block7crudvalidation.clase.profesor.aplicacion.ProfesorServiceImp;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorInput;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutPutFather;
import com.example.block7crudvalidation.clase.profesor.controller.dto.ProfesorOutputSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Profesores")
@RestController
public class ControladorProfesor {
    @Autowired
    private ProfesorServiceImp profesorService;

    @GetMapping("/buscar/{id}")
    public ProfesorOutputSimple buscarProfesor(@PathVariable Long id) {
        return profesorService.buscarProfesor(id);
    }

    @GetMapping("/listar")
    public List<ProfesorOutPutFather> listarPersonas(@RequestParam("outputType") String outputType) {
        if (outputType.equalsIgnoreCase("full")) {
            List<ProfesorOutPutFather> profesorOutPutFathers = profesorService.listarProfesores(outputType);
            return new ArrayList<>(profesorOutPutFathers);
        } else {
            List<ProfesorOutPutFather> profesorOutPutFathers = profesorService.listarProfesores(outputType);
            return new ArrayList<>(profesorOutPutFathers);
        }
    }

    @PostMapping("/agregar")
    public ProfesorOutputSimple addProfesor(@RequestBody ProfesorInput profesorInput) throws Exception {

        return profesorService.addProfesor(profesorInput);

    }
    @DeleteMapping("/borrar/{id}")
    public void deleteProfesor(@PathVariable Long id) {
        profesorService.deletePersonaById(id);

    }
    @PutMapping("/cambiar/{id}")
    public ProfesorOutputSimple updateProfesor(@PathVariable Long id, @RequestBody ProfesorInput profesorInput) {
        ProfesorOutputSimple profesorOutputSimple = profesorService.updatePersona(id, profesorInput);
        return profesorOutputSimple;
    }

}
