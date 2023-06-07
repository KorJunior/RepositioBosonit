package com.example.block11uploaddownloadfiles.Fichero;

import com.example.block11uploaddownloadfiles.Fichero.dominio.FicheroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicheroRepositorio extends JpaRepository<FicheroEntity, Long> {

}
