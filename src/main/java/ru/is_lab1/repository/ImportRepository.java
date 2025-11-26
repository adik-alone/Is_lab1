package ru.is_lab1.repository;

import ru.is_lab1.entity.Import;
import ru.is_lab1.entity.User;

import java.util.List;
import java.util.Optional;

public class ImportRepository extends AbstractRepository<Import> {
    @Override
    public Optional<Import> findById(Long id) {
        return Optional.ofNullable(em.find(Import.class, id));
    }

    @Override
    public List<Import> findAll() {
        return em.createQuery("SELECT i FROM Import i", Import.class)
                .getResultList();
    }

    @Override
    public List<Import> findPage(int page, int size) {
        return em.createQuery("SELECT i FROM Import i", Import.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public List<Import> findAllByUserId(Long id){
        return em.createQuery("SELECT i FROM Import i WHERE i.userId = :userId", Import.class)
                .setParameter("userId", id)
                .getResultList();
    }
}
