package com.example.repository;

import com.example.entity.Person;
import java.util.List;
import java.util.Optional;

public class PersonRepository extends AbstractRepository<Person>{
    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(em.find(Person.class, id));
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("SELECT p FROM ms_person p", Person.class)
                .getResultList();
    }

    @Override
    public List<Person> findPage(int page, int size) {
        return em.createQuery("SELECT p FROM ms_person p", Person.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
