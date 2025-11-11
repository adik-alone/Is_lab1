package ru.is_lab1.repository;

import ru.is_lab1.entity.Person;
import java.util.List;
import java.util.Optional;

public class PersonRepository extends AbstractRepository<Person>{
    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(em.find(Person.class, id));
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }

    @Override
    public List<Person> findPage(int page, int size) {
        return em.createQuery("SELECT p FROM Person p", Person.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Person> directorsWithoutOscars(){
        return em.createQuery("SELECT p FROM Person p " +
                                "where NOT EXISTS(SELECT m FROM Movie m WHERE m.director = p AND m.oscarsCount > 0)", Person.class)
                .getResultList();
    }
}
