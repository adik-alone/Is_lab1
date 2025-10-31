package ru.is_lab1.repository;

import ru.is_lab1.entity.Location;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class LocationRepository extends AbstractRepository<Location>{
    @PersistenceContext(unitName = "myPostgresPU")
    EntityManager em;

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.ofNullable(em.find(Location.class, id));
    }

    @Override
    public List<Location> findAll() {
        return em.createQuery("SELECT l FROM Location l", Location.class)
                .getResultList();
    }

    @Override
    public List<Location> findPage(int page, int size){
        return em.createQuery("SELECT l FROM Location l", Location.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
