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



    static Persona insertarPersona(Request req, Response res) {
        //Esta línea establece el tipo de contenido de la respuesta HTTP a application/json.
        // Esto le indica al cliente que la respuesta está en formato JSON.
        res.type("application/json");
       //Utiliza el contenedor CDI (container) para obtener una instancia del servicio ServicioPersona
        var servicio = container.select(ServicioPersona.class).get();


        // Leer el cuerpo de la solicitud y convertirlo en un objeto Persona
       Persona persona = new Gson().fromJson( req.body(), Persona.class);

        // Insertar la persona
        servicio.insert(persona);
        res.status(201);

        return persona;
    }



   static Persona actualizarPersona(Request req, Response res) {
       res.type("application/json");
       var servicio = container.select(ServicioPersona.class).get();

       // Verificar si el cuerpo de la solicitud está presente
       if (req.body() == null || req.body().isEmpty()) {
           halt(400, "Cuerpo de la solicitud vacío");
       }

       // Leer el cuerpo de la solicitud y convertirlo en un objeto Persona
       Persona persona = new Gson().fromJson(req.body(), Persona.class);

       // Verificar si el cuerpo de la solicitud contiene los datos necesarios de la persona
       if (persona == null) {
           halt(400, "Datos de la persona no proporcionados en el cuerpo de la solicitud");
       }


       // Actualizar la persona
       servicio.update(persona);


       res.status(200); // Establecer el código de estado de la respuesta a 200 (OK)
       return persona; // Devolver la persona actualizada en formato JSON
   }

   public static Persona eliminarPersona(Request req, Response res) {
        res.type("application/json");

        String _id = req.params(":id");

        var servicio = container.select(ServicioPersona.class).get();

       var persona = servicio.findId(Integer.valueOf(_id));

       if(persona == null){
           halt(404,"Persona no encontrada");
       }else{
           servicio.delete(persona.getId());
           halt(200, "Persona eliminada correctamente ");
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
        post("/personas", Main::insertarPersona,gson::toJson);
        put("/personas/:id", Main::actualizarPersona, gson::toJson);

        delete("/personas/:id", Main::eliminarPersona, gson::toJson);



    }
}