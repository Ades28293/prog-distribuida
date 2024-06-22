package com.distribuida;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDate;

public class Main {
    //cREAR MAS CONTENEDORES INJECT
    @Inject
    @ConfigProperty(name = "app.books.msg",defaultValue = "xx")
    private String message;
    @GET
    public String main() {
//        System.out.println("Hello world!");
//        Config config= ConfigProvider.getConfig();
//        config.getConfigSources().forEach(t->{
//            System.out.printf("%d: %s:",  t.getOrdinal(), t.getName());
//
//        });
//
//        var msg= config.getValue("app.book.msg",String.class);
//        System.out.println(msg);
//        return "Hola"+ LocalDate.now();
        System.out.println(message);
        return message+ LocalDate.now();
    }
}