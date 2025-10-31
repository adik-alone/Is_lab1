package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.CoordinatesRequest;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.mapper.CoordinatesMapper;
import ru.is_lab1.repository.CoordinatesRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CoordinatesService {

    private final Logger logger = LoggerFactory.getLogger(CoordinatesService.class);

    @Inject
    CoordinatesRepository repository;

    @Inject
    CoordinatesMapper mapper;

    @Transactional
    public Coordinates createCoordinates(CoordinatesRequest request){
        Coordinates createdCoordinates = mapper.toEntity(request);
        return repository.save(createdCoordinates);
    }
    public Coordinates getCoordinatesById(Long id){
        Optional<Coordinates> optionalCoordinates = repository.findById(id);
        if (optionalCoordinates.isEmpty()){
            throw new RuntimeException("Coordinates not found");
        }
        return optionalCoordinates.get();
    }

    public List<Coordinates> getPageCoordinates(int page, int size){
        return repository.findPage(page, size);
    }

    @Transactional
    public Coordinates updateCoordinates(Long id, CoordinatesRequest request){
//        logger.info("CoordinatesService.updateCoordinates: start");
        Optional<Coordinates> optionalCoordinates = repository.findById(id);
        if (optionalCoordinates.isEmpty()){
            throw new RuntimeException("Coordinates not found");
        }
//        logger.info("CoordinatesService.updateCoordinates: old_coordinates = " + coordinates);
        Coordinates coordinates = optionalCoordinates.get();
        mapper.updateEntity(request, coordinates);
//        logger.info("CoordinatesService.updateCoordinates: new_coordinates = " + coordinates);
        return repository.update(coordinates);
    }

    @Transactional
    public void deleteCoordinates(Long id){
        if (!repository.delete(id)){
            throw new RuntimeException("Coordinates not found");
        }
    }
}
