package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.repo.AuthorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AuthorRest {

    @Inject
    AuthorRepository authorRepository;

    @GET
    public List<Author> findAll() {
        System.out.println("findAll");
        System.out.println("**************************");

        return authorRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var op = authorRepository.findByIdOptional(id);
        if (op == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Author author) {
        author.setId(null);
        authorRepository.persist(author);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Author author) {
        var obj = authorRepository.findById(id);
        obj.setId(author.getId());
        obj.setFirstName(author.getFirstName());
        obj.setLastName(author.getLastName());
        obj.setAge(author.getAge());
        obj.setMail(author.getMail());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        authorRepository.deleteById(id);
        return Response.ok().build();
    }
}
