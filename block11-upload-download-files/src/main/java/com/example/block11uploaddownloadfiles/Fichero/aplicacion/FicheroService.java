package com.example.block11uploaddownloadfiles.Fichero.aplicacion;

import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroInput;
import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroOutPut;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface FicheroService {
    FicheroOutPut buscarFichero(Long id);

    List<FicheroOutPut> listarFicheros(String outputType);

    FicheroOutPut addFicheros(MultipartFile file,String tipo,String categoria) throws Exception;
    void borrarFicherosid(Long id);

    FicheroOutPut actualizarFicheros(Long id, FicheroInput persona);
}
