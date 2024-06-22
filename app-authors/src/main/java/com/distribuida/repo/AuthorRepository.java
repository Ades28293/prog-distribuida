package com.distribuida.repo;

import com.distribuida.db.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuthorRepository implements PanacheRepositoryBase<Author, Integer> {
   /*
   PanacheRepositoryBase, hereda métodos CRUD básicos (Create, Read, Update, Delete)
   para gestionar las entidades Author de forma simplificada.
    */


}
