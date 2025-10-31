package ru.is_lab1.repository;

import ru.is_lab1.entity.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
