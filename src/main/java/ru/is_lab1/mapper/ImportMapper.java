package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.upload.UploadMovie;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.Person;

@ApplicationScoped
public class ImportMapper {
    private final Logger logger = LoggerFactory.getLogger(ImportMapper.class);

    @Inject
    private MovieMapper movieMapper;
    @Inject
    private ModelMapper modelMapper;

    public Movie uploadMovieToEntity(UploadMovie uploadMovie){
        logger.info("uploadMovieToEntity: start");
        logger.info("uploadMovieToEntity: to movieMapper");
        Movie movie = movieMapper.uploadToEntity(uploadMovie);
        logger.info("uploadMovieToEntity: movieMapper done");
        Coordinates coordinates = modelMapper.map(uploadMovie.getCoordinates(), Coordinates.class);
        Person director = modelMapper.map(uploadMovie.getDirector(), Person.class);
        if (uploadMovie.getScreenwriter() != null) {
            Person screenwriter = modelMapper.map(uploadMovie.getScreenwriter(), Person.class);
            movie.setScreenwriter(screenwriter);
        }
        Person operator = modelMapper.map(uploadMovie.getOperator(), Person.class);

        movie.setCoordinates(coordinates);
        movie.setDirector(director);
        movie.setOperator(operator);
        return movie;
    }
}
