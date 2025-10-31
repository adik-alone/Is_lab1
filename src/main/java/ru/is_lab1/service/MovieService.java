package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import ru.is_lab1.dto.request.MovieRequest;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.repository.MovieRepository;

@ApplicationScoped
public class MovieService {
//    @Inject
//    MovieRepository repository;
//
//    @Inject
//    ModelMapper modelMapper;
//
//    @Transactional
//    public Movie createMovie(MovieRequest request){
//        Movie createdMovie = modelMapper.map(request, Movie.class);
//        return repository.save(createdMovie);
//    }
//
////    public Movie getMovieById(Long id)
//
}
