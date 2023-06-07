package com.example.block11uploaddownloadfiles.Fichero.controlador;

import com.example.block11uploaddownloadfiles.Fichero.aplicacion.FicheroServiceImplem;
import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class FicheroControlador {

    @Autowired
    private FicheroServiceImplem ficheroServiceImplem;



    @PostMapping("/upload/{tipo}")
    public FicheroOutPut subirFichero(@RequestParam("file") MultipartFile file,
                                      @PathVariable String tipo,
                                      @RequestParam String categoria) throws Exception {
       return ficheroServiceImplem.addFicheros(file,tipo,categoria);
    }
    @GetMapping("/dowload/{id}")
    public void descargarArchivoPorId(@PathVariable Long id) throws FileNotFoundException, IOException {
        ficheroServiceImplem.descargarArchivoPorId(id);
    }
    @GetMapping("/dowload2/{name}")
    public void descargarArchivoPorNombre(@PathVariable String name) throws FileNotFoundException, IOException {
        ficheroServiceImplem.descargarArchivoPorNombre(name);
    }
    @GetMapping("/saveDowloadData")
    public void setPath(@RequestParam String path) {
        ficheroServiceImplem.setData(path);
    }

    @GetMapping("/saveData")
    public void setData(@RequestParam String path) {
        ficheroServiceImplem.setPath(path);
    }
}