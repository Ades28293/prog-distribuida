package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {

    public Persona findById(Integer id);

    public List<Persona> findAall();

    public void create(Persona persona);

}
