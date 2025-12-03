package ru.is_lab1.repository;

import jakarta.transaction.Transactional;
import ru.is_lab1.entity.User;

import javax.swing.text.html.Option;
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
    public Optional<User> findByLogin(String login){
        try {
            return Optional.ofNullable(em.createQuery("SELECT u FROM User u WHERE u.login LIKE :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult());
        }catch (Exception e){
            return Optional.empty();
//            throw new RuntimeException("Error with finding User: " + e.getMessage());
        }
    }
}
