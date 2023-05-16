package com.example.block5profiles;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${bd.url}")
    private String hola;


    public AppConfig() {

    }

    public String getName() {
        return hola;
    }


}
