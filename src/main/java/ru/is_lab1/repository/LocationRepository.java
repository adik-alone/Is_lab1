package com.example.repository;

import com.example.entity.AbstractEntity;
import com.example.entity.Location;
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
        return em.createQuery("SELECT l FROM ms_location l", Location.class)
                .getResultList();
    }

    @Override
    public List<Location> findPage(int page, int size){
        return em.createQuery("SELECT l FROM ms_location l", Location.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
