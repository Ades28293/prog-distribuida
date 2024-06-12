package com.distribuida;

import com.distribuida.config.AppConfig;
import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        var servicio=context.getBean(ServicioPersona.class);

        System.out.println(servicio);

        var p=new Persona();
        p.setId(1);
        p.setNombre("Pepe");

        servicio.create(p);

        System.out.println("--Listas de personas");

        servicio.findAall().stream().map(Persona::getNombre).forEach(System.out::println);


    }
}