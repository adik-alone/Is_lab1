package ru.is_lab1.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.ImportRequest;
import ru.is_lab1.dto.request.upload.UploadMovie;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.entity.Import;
import ru.is_lab1.entity.Movie;
import ru.is_lab1.entity.Person;
import ru.is_lab1.entity.enums.Role;
import ru.is_lab1.exceptions.exception.ImportException;
import ru.is_lab1.mapper.ImportMapper;
import ru.is_lab1.repository.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ImportService {
    private final Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Inject
    ImportRepository repository;

    @Inject
    ImportMapper mapper;

    @Inject
    private ModelMapper modelMapper;

    @Inject
    MovieRepository movieRepository;
    @Inject
    LocationRepository locationRepository;
    @Inject
    CoordinatesRepository coordinatesRepository;
    @Inject
    PersonRepository personRepository;

    @Inject
    MovieService movieService;

    @Inject
    MinIOService minIOService;

    @Transactional
    public Pair<List<Movie>, String> importMovies(InputStream file, Long userId){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            IOUtils.copy(file, baos);
        }catch (IOException e){
            logger.error("Error while coping source file", e);
            throw new RuntimeException(e);
        }
        InputStream fileMinio = new ByteArrayInputStream(baos.toByteArray());
        InputStream fileDB = new ByteArrayInputStream(baos.toByteArray());


        logger.info("importMovies: start");
        Pair<String, String> pair = minIOService.uploadFile(fileMinio);
        try{
            List<Movie> movies = saveMovie(fileDB);
            createImportResult(new ImportRequest(true, (long) movies.size(), pair.getLeft()), userId);
            return Pair.of(movies, pair.getLeft());
        }catch (Exception e) {
            minIOService.deleteFile(pair.getRight());
            createImportResult(new ImportRequest(false, 0L, null), userId);
            logger.info("importMovies: something fails");
            throw new ImportException("Error while reading file: " + e.getMessage());
        }
    }
    @Transactional
    private List<Movie> saveMovie(InputStream file){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            logger.info("importMovies: start convert to uploadMovies");
            List<UploadMovie> moviesRequest = objectMapper.readValue(file, new TypeReference<>() {
            });
            logger.info("importMovies: convert to uploadMovies entity success");
            List<Movie> movies = new ArrayList<>();
            logger.info("importMovies: start converting to Movie entity one by one");
            for (UploadMovie uploadMovie : moviesRequest) {
                Movie movie = mapper.uploadMovieToEntity(uploadMovie);
                Person director = movie.getDirector();
                Person screenwriter = movie.getScreenwriter();
                Person operator = movie.getOperator();
                Coordinates coordinates = movie.getCoordinates();

                if (screenwriter != null) {
                    locationRepository.save(screenwriter.getLocation());
                    personRepository.save(screenwriter);
                }
                locationRepository.save(director.getLocation());
                locationRepository.save(operator.getLocation());
                personRepository.save(operator);
                personRepository.save(director);

                coordinatesRepository.save(coordinates);
                movie.setCreationDate(LocalDate.now());

                movieRepository.save(movie);
                movies.add(movie);
            }
            return movies;
        }catch (IOException e){
            logger.error("Error while reading file", e);
            throw new ImportException("Error while reading file: " + e.getMessage());
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Import createImportResult(ImportRequest request, Long id ){
        Import importResult = modelMapper.map(request, Import.class);
        importResult.setUserId(id);
        return repository.save(importResult);
    }

    public List<Import> getAll(){
        return repository.findAll();
    }

    public List<Import> getImports(Role role, Long id){
        if (role.equals(Role.ADMIN)){
            return repository.findAll();
        }
        return repository.findAllByUserId(id);
    }
}
