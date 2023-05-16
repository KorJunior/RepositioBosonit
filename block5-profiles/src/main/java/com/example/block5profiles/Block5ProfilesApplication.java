package com.example.block5profiles;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Block5ProfilesApplication {

    @Value("${bd.url}")
    public String name;
    public static void main(String[] args) {
        SpringApplication.run(com.example.block5profiles.Block5ProfilesApplication.class, args);}


    @Bean
    CommandLineRunner ejecutame()
    {
        return p ->
        {
            System.out.println(name);
        };
    }


}
