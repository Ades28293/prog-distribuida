package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {

    Persona findId(int id);
    List<Persona> findAll();

    void insert(Persona persona);
    void update(Persona persona);
    Persona delete(Integer id);

}
