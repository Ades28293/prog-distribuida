package com.distribuida.clients;


import com.distribuida.dto.AuthorDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "http://localhost:9090")
@RegisterRestClient(configKey = "AuthorRestClient")
/*
Registra esta interfaz como un cliente REST con la URI
base http://localhost:9090. Esto significa que todas las
llamadas hechas a través de este cliente REST se dirigirán a este URI base.
 */
public interface AuthorRestClient {

    /*
    El código proporcionado define una interfaz AuthorRestClient
    en el contexto de una aplicación Java que utiliza JAX-RS
    para servicios web RESTful y la biblioteca MicroProfile Rest Client
    para realizar llamadas a servicios REST externos.
     */


    //Copia de servicio en el servidor
    //Mapeamos todas sus funciones de la original GET
    //@GET
    //public List<Objects> findAll();

    @GET
    @Path("/{id}")
    AuthorDTO findById(@PathParam("id") Integer id);
}
