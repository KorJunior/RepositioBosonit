package com.example.block11uploaddownloadfiles.Fichero.controlador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheroOutPut {
    private Long id;
    private String nombre;
    private Date fechaSubida;
    private String categoria;
}
