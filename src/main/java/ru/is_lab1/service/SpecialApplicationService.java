package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.Person;
import ru.is_lab1.entity.enums.MovieGenre;
import ru.is_lab1.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SpecialApplicationService {
    private final Logger logger = LoggerFactory.getLogger(SpecialApplicationService.class);
    @Inject
    MovieRepository repository;

    @Transactional
    public void deleteOneMovieGenre(MovieGenre genre){
        logger.info("SpecialService.deleteOneMovieGenre: start");
        Optional<Movie> optionalMovie = repository.getOneMovieByGenre(genre.toString());
        if (optionalMovie.isEmpty()){
            throw new RuntimeException("Movie with this genre not found");
        }
        Movie deletingMovie = optionalMovie.get();
        logger.info("SpecialService.deleteOneMovieGenre: current Movie ==== " + deletingMovie);
        if (!repository.delete(deletingMovie.getId())){
            throw new RuntimeException("Deleting ends with error, try again");
        }
        logger.info("SpecialService.deleteOneMovieGenre: end");
    }

//    public List<Long> groupMovieByTotalBoxOffice(){
//
//    }
//
//    public Long movieWithGoldenPalmEqualsValue(){
//
//    }
//
//    public List<Person> getDirectorsWithoutOscars(){
//
//    }
//
//    public void reBalanceOscarsInOtherGenre(){
//
//    }

}
