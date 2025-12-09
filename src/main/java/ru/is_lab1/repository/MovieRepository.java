package ru.is_lab1.repository;

import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.cache.L2Cached;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.enums.MovieGenre;

import java.util.List;
import java.util.Optional;

public class MovieRepository extends AbstractRepository<Movie>{
    private final Logger logger = LoggerFactory.getLogger(MovieRepository.class);

    @Override
    public Optional<Movie> findById(Long id) {
        logger.info("findById");
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .getResultList();
    }

    @Override
    public List<Movie> findPage(int page, int size) {
        logger.info("findPage");
        return em.createQuery("SELECT m FROM Movie m", Movie.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Optional<List<Movie>> getListMovieByGenre(String genre){
        return Optional.ofNullable(em.createQuery("SELECT m FROM Movie m WHERE m.genre LIKE :genre", Movie.class)
                .setParameter("genre", genre)
                .getResultList()
        );
    }

    public List<Long> groupMovieByTotalBoxOffice(){
        return em.createQuery("SELECT count(m) FROM Movie m GROUP BY m.totalBoxOffice", Long.class)
                .getResultList();
    }

    public Optional<Long> movieWithGoldenPalmEqualsValue(int value){
        return Optional.ofNullable(em.createQuery("SELECT count(m) FROM Movie m WHERE m.goldenPalmCount = :value", Long.class)
                .setParameter("value", value)
                .getSingleResult());
    }

    public List<Movie> findWithFilterAndSorted(int page, int size, String filterColumn, String filterValue, String sortedColumn, boolean asc) {
        String query = "SELECT m FROM Movie m";
        String filter = "";
        String sorted = "";
        String ascStr = " ASC";

        boolean isFilterValid = (filterColumn != null && filterValue != null) && (!filterColumn.isEmpty() && !filterValue.isEmpty());

        if(isFilterValid){
            filter = " WHERE m." + filterColumn.toLowerCase() + " LIKE :filterValue";
            query += filter;
        }

        if (sortedColumn != null && !sortedColumn.isEmpty()){
            sorted = " ORDER BY m." + sortedColumn;
            if (!asc){
                ascStr = " DESC";
            }
            sorted = sorted + ascStr;
            query += sorted;
        }
        TypedQuery<Movie> typedQuery = em.createQuery(query, Movie.class);

        if (isFilterValid){
            typedQuery.setParameter("filterValue", filterValue);
        }
        typedQuery
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);
        return typedQuery.getResultList();
    }
}
