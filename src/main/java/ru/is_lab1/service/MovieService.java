package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.MovieRequest;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.exceptions.exception.NotFoundException;
import ru.is_lab1.exceptions.exception.RepositoryException;
import ru.is_lab1.mapper.MovieMapper;
import ru.is_lab1.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService {
    private final Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Inject
    MovieRepository repository;

    @Inject
    MovieMapper mapper;

    @Transactional
    public Movie createMovie(MovieRequest request){
        Movie createdMovie = mapper.toEntity(request);
        createdMovie.setCreationDate(java.time.LocalDate.now());
        return repository.save(createdMovie);
    }

    public Movie getMovieById(Long id) throws NotFoundException{
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty()){
            throw new NotFoundException("Movie not found");
        }
        return optionalMovie.get();
    }

    public List<Movie> getPageMovie(int page, int size){
        return repository.findPage(page, size);
    }

    public List<Movie> getPageFilterAndSortMovie(int page, int size,
                                                 String filterColumn, String filterValue,
                                                 String sortedColumn,
                                                 boolean asc){
        return repository.findWithFilterAndSorted(page, size, filterColumn, filterValue, sortedColumn, asc);

    }

    @Transactional
    public Movie updateMovie(Long id, MovieRequest request) throws NotFoundException{
        logger.info("MovieService.updateMovie: start");
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty()){
            throw new NotFoundException("Movie not found");
        }
        Movie updatedMovie = optionalMovie.get();
        mapper.updateEntity(request, updatedMovie);
        logger.info("MovieService.updateMovie: end");
        return repository.update(updatedMovie);
    }

    @Transactional
    public void deleteMovie(Long id)throws NotFoundException {
        if(!repository.delete(id)){
            throw new NotFoundException("Movie not found");
        }
    }
}
