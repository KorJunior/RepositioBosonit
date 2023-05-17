package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador/bean")
public class Controlador {

    private Persona bean1;
    private Persona bean2;
    private Persona bean3;

    @Autowired
    public Controlador(@Qualifier("bean1") Persona bean1, @Qualifier("bean2") Persona bean2, @Qualifier("bean3") Persona bean3) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
    }


    @GetMapping("/{bean}")
    public Persona obtenerBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return bean1;
            case "bean2":
                return bean2;
            case "bean3":
                return bean3;
            default:
                return null;
        }
    }

}


