package com.distribuida.service;

import com.distribuida.db.Persona;

import java.util.List;

public interface PersonaService {

    public Persona insert(Persona persona);
    public Persona update(Persona persona);

    public void delete(Integer id);

    public Persona findById(Integer id);

    public List<Persona> findAll();
}
