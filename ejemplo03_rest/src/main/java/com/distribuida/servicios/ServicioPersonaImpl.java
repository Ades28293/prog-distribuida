package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {

    @Inject
    EntityManager em;

    @Override
    public Persona findId(int id) {
        return em.find(Persona.class,id);
    }

    @Override
    public List<Persona> findAll() {
        return em.createQuery("select p from Persona p order by id asc", Persona.class).getResultList();
    }

    @Override
    public void insert(Persona persona) {
        System.out.println("Se ha insertado el Persona: " + persona);
         em.persist(persona);
    }


    @Override
    public void update(Persona persona) {
        System.out.println("Se ha actualizado la Persona: " + persona.getId());
        em.merge(persona);
    }

    @Override
    public Persona delete(Integer id) {

        em.remove(em.find(Persona.class, id));
        return null;
    }
}
