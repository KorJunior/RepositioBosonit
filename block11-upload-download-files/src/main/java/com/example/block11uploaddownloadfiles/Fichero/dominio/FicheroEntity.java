package com.example.block11uploaddownloadfiles.Fichero.dominio;

import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroInput;
import com.example.block11uploaddownloadfiles.Fichero.controlador.dto.FicheroOutPut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fichero")
public class FicheroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private Date fechaSubida;
    private String categoria;

    public FicheroEntity(FicheroInput ficheroInput) {
        this.nombre = ficheroInput.getNombre();
        this.fechaSubida = ficheroInput.getFechaSubida();
        this.categoria = ficheroInput.getCategoria();
    }
    public FicheroOutPut parseFicheroOutput(){
        return new FicheroOutPut(this.id, this.nombre, this.fechaSubida, this.categoria);
    }

}
