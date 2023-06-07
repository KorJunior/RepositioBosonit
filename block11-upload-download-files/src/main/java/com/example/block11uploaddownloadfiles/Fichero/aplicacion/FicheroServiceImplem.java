package com.example.block11uploaddownloadfiles.Fichero.aplicacion;

import com.example.block11uploaddownloadfiles.Fichero.FicheroRepositorio;
import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroInput;
import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroOutPut;
import com.example.block11uploaddownloadfiles.Fichero.dominio.FicheroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class FicheroServiceImplem implements FicheroService{
    @Autowired
    FicheroRepositorio ficheroRepository;

    private String directorioDescarga; // Variable para almacenar la ruta de directorio de guardado
    private String directorioGuardado; // Variable para almacenar la ruta de directorio de guardado

    @Override
    public FicheroOutPut buscarFichero(Long id) {
        return null;
    }

    @Override
    public List<FicheroOutPut> listarFicheros(String outputType) {
        return null;
    }

    @Override
    public FicheroOutPut addFicheros(MultipartFile file, String tipo, String categoria) throws Exception {
        String nombreArchivo = file.getOriginalFilename();
        Path path = Paths.get(nombreArchivo);
        String nombreArchivoString = path.getFileName().toString();
        int indicePunto = nombreArchivoString.lastIndexOf(".");
        String extension;
        FicheroEntity fichero = new FicheroEntity();
        String rutaFichero;

        extension =obtenerExtension(nombreArchivoString);

        if (!extension.equals(tipo)) {
            throw new IllegalArgumentException("Tipo de fichero inválido");
        }
        if (getPath() == null) {
            throw new IllegalStateException("Primero debe establecer la ruta de destino");
        }
        rutaFichero = getPath() + "/" + file.getOriginalFilename();
        File destino = new File(rutaFichero);
        file.transferTo(destino);
        // Crear la entidad Fichero y guardarla en la base de datos

        fichero.setNombre(file.getOriginalFilename());
        fichero.setFechaSubida(new Date());
        fichero.setCategoria(categoria);
        ficheroRepository.save(fichero);

        // Devolver la entidad Fichero con el ID generado
        return fichero.parseFicheroOutput();
    }
    private String obtenerExtension(String nombreArchivo) {
        int indicePunto = nombreArchivo.lastIndexOf(".");
        if (indicePunto == -1) {
            throw new IllegalArgumentException("El nombre de archivo no tiene una extensión válida");
        }
        return nombreArchivo.substring(indicePunto + 1);
    }
    public void descargarArchivoPorId(Long id) throws IOException {
        String rutaFichero;
        FicheroEntity fichero;

        fichero = ficheroRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("No se encontró el archivo con el ID especificado"));

        // Obtener la ruta del fichero en el sistema de archivos
        rutaFichero = getPath() + "/" + fichero.getNombre();
        File archivo = new File(rutaFichero);

        String rutaDestino = getData() + "/" + fichero.getNombre();
        File destino = new File(rutaDestino);
        Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }
    public void descargarArchivoPorNombre(String nombre) throws IOException {
        List<FicheroEntity> ficheros = ficheroRepository.findAll();
        FicheroEntity fichero;
        Optional<FicheroEntity> ficheroOptional;
        String rutaFichero;
        File archivo,destino;


        ficheroOptional = ficheros.stream()
                .filter(f -> f.getNombre().equals(nombre))
                .findFirst();
         fichero = ficheroOptional.orElseThrow(() -> new FileNotFoundException("No se encontró el archivo con el nombre especificado"));
         rutaFichero = getPath() + "/" + fichero.getNombre();archivo = new File(rutaFichero);

        String rutaDestino = getData() + "/" + fichero.getNombre();
        destino = new File(rutaDestino);
        Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }



    public void setPath(String path) {
        directorioGuardado = path;
    }
    public void setData(String path) {
        directorioDescarga = path;
    }
    public String getData() {
        return directorioDescarga;
    }
    public String getPath(){
        return directorioGuardado;
    }

    @Override
    public void borrarFicherosid(Long id) {

    }

    @Override
    public FicheroOutPut actualizarFicheros(Long id, FicheroInput persona) {
        return null;
    }
}
