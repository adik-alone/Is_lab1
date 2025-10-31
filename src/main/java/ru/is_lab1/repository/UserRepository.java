package ru.is_lab1.repository;

import ru.is_lab1.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class UserRepository {

    @PersistenceContext(unitName = "myPostgresPU")
    private EntityManager em;

    public User save(User user){
        em.getTransaction().begin();
        if(user.getId() == null){
            em.persist(user);
            em.flush();
            em.getTransaction().commit();
            return user;
        }else{
            User updated = em.merge(user);
            if (updated == user) System.out.println("1. Entity equals");
            else System.out.println("1. Entity isn't equals");
            if (updated.equals(user)) System.out.println("2. Entity equals");
            else System.out.println("2. Entity isn't equals");
            em.flush();
            em.getTransaction().commit();
            return updated;
        }
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(em.find(User.class, id));
    }

    public List<User> findAll(){
        return em.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    public Optional<User> findByUsername(String username){
        return em.createNamedQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public void delete(Long id){
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null){
            em.remove(user);
            em.flush();
            em.getTransaction().commit();
        }
    }

}
