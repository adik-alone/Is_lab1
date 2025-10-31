package ru.is_lab1.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.enums.MovieGenre;

import java.util.List;
import java.util.Optional;

public class MovieRepository extends AbstractRepository<Movie>{
    private final Logger logger = LoggerFactory.getLogger(MovieRepository.class);

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .getResultList();
    }

    @Override
    public List<Movie> findPage(int page, int size) {
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Optional<Movie> getOneMovieByGenre(String genre){
        return Optional.ofNullable(em.createQuery("Select m FROM Movie m WHERE m.genre LIKE :genre", Movie.class)
                .setParameter("genre", genre)
                .getSingleResult());
    }
}
