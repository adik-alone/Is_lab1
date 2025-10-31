package ru.is_lab1.repository;

import ru.is_lab1.entity.Coordinates;

import java.util.List;
import java.util.Optional;

public class CoordinatesRepository extends AbstractRepository<Coordinates> {
    @Override
    public Optional<Coordinates> findById(Long id){
        return Optional.ofNullable(em.find(Coordinates.class, id));
    }
    @Override
    public List<Coordinates> findAll(){
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .getResultList();
    }

    @Override
    public List<Coordinates> findPage(int page, int size){
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
