package com.distribuida.repository;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PersonaRepositoryImpl implements PersonaRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Persona insert(Persona persona) {
        this.entityManager.persist(persona);
        return persona;
    }

    @Override
    public Persona update(Persona persona) {

        this.entityManager.merge(persona);


        return persona;
    }

    @Override
    public void delete(Integer id) {
        this.entityManager.remove(entityManager.find(Persona.class, id));
    }

    @Override
    public Persona findById(Integer id) {

        return this.entityManager.find(Persona.class, id);
    }

    @Override
    public List<Persona> findAll() {
        TypedQuery<Persona>myQuery=entityManager.createQuery("SELECT p FROM Persona p ORDER BY p.id ASC", Persona.class);
        return myQuery.getResultList();
    }
}
