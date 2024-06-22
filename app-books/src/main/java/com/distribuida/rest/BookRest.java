package com.distribuida.rest;

import com.distribuida.clients.AuthorRestClient;
import com.distribuida.db.Book;
import com.distribuida.dto.BookDTO;
import com.distribuida.repo.BookRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(("/books"))
public class BookRest {

    @Inject
    BookRepository bookRepository;

    @Inject
    @RestClient
    AuthorRestClient authorRest;

    @GET
    public List<BookDTO> findAll() {

        var book = bookRepository.listAll();
        book.stream().forEach(obj -> {
            //BookDTO bdto = new BookDTO();

            var author=authorRest.findById(obj.getAuthorId());
            BookDTO bookDTO=new BookDTO();
            bookDTO.setId(obj.getAuthorId());
            bookDTO.setIsbn(obj.getIsbn());
            bookDTO.setTitle(obj.getTitle());
            bookDTO.setPrice(obj.getPrice());

            bookDTO.setAuthorName(author.getFirstName());
            //buscar el autor obj.getAuthorId()
            //Conectarse al servicio "app-authors"
            //http://localhost:8081/author/{id}
            //bdto.setAuthorId();
            //.....
        });

        return List.of();
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var op = bookRepository.findByIdOptional(id);
        if (op == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(op.get()).build();
    }

    @POST
    public Response create(Book book) {
        book.setId(null);
        bookRepository.persist(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Book book) {
        var obj = bookRepository.findById(id);
        obj.setIsbn(book.getIsbn());
        obj.setTitle(book.getTitle());
        obj.setPrice(book.getPrice());
        obj.setAuthorId(book.getAuthorId());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        bookRepository.deleteById(id);
        return Response.ok().build();
    }

}
