package com.example.repository;

import com.example.entity.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {
    @PersistenceContext(unitName = "myPostgresPU")
    EntityManager em;
    public T save(T t){
        em.getTransaction().begin();
        em.persist(t);
        em.flush();
        em.getTransaction().commit();
        return t;
    }

    public T update(T t){
        em.getTransaction().begin();
        em.merge(t);
        em.flush();
        em.getTransaction().commit();
        return t;
    }

    public boolean delete(Long id){
        var t = findById(id);
        if (t.isEmpty()) return false;
        em.getTransaction().begin();
        em.remove(t.get());
        em.getTransaction().commit();
        return true;
    }
}
