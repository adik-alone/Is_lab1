package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.MovieRequest;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.mapper.MovieMapper;
import ru.is_lab1.repository.MovieRepository;

import java.util.Date;
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

    public Movie getMovieById(Long id){
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty()){
            throw new RuntimeException("Movie not found");
        }
        return optionalMovie.get();
    }

    public List<Movie> getPageMovie(int page, int size){
        return repository.findPage(page, size);
    }

    @Transactional
    public Movie updateMovie(Long id, MovieRequest request){
        logger.info("MovieService.updateMovie: start");
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty()){
            throw new RuntimeException("Movie not found");
        }
        Movie updatedMovie = optionalMovie.get();
        mapper.updateEntity(request, updatedMovie);
        logger.info("MovieService.updateMovie: end");
        return updatedMovie;
    }

    @Transactional
    public void deleteMovie(Long id){
        if(!repository.delete(id)){
            throw new RuntimeException("Movie not found");
        }
    }

}
