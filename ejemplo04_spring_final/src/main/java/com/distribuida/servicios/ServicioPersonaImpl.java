package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioPersonaImpl implements ServicioPersona{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Persona findById(Integer id){
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> findAall(){
        return em.createQuery("select p from Persona p order by id asc", Persona.class).getResultList();
    }

    public void create(Persona persona){
        em.persist(persona);
    }


}
