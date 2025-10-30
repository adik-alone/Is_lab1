package com.example.repository;

import com.example.entity.Movie;

import java.util.List;
import java.util.Optional;

public class MovieRepository extends AbstractRepository<Movie>{
    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM ms_movie m", Movie.class)
                .getResultList();
    }

    @Override
    public List<Movie> findPage(int page, int size) {
        return em.createQuery("SELECT m FROM ms_movie m", Movie.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
