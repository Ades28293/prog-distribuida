package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

//Framework
import java.util.List;

import static spark.Spark.*;
public class Main {

    static SeContainer container;

    static List<Persona> listarPersonas(Request req, Response res) {
        res.type("application/json");
        var servicio = container.select(ServicioPersona.class)
                .get();

        return  servicio.findAll();
    }

    static  Persona buscarPersona(Request req, Response res) {
        res.type("application/json");
        String _id = req.params(":id");
        var servicio = container.select(ServicioPersona.class)
                .get();

        var persona = servicio.findId(Integer.valueOf(_id));

        if(persona == null){
            halt(404,"Persona no encontrada");
        }

        return persona;
    }

    public static void main(String[] args) {
        container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class)
                .get();

        servicio.findAll()
                .stream().map(Persona::getNombre)
                .forEach(System.out::println);

        port(8080);
        //get("/hello", (req, res) -> "Hello World");

        Gson gson = new Gson();


        get("/personas", Main::listarPersonas, gson::toJson);
        get("/personas/:id", Main::buscarPersona, gson::toJson);



    }
}