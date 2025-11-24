package ru.is_lab1.repository;

import ru.is_lab1.entity.User;

import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public List<User> findPage(int page, int size) {
        return em.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
