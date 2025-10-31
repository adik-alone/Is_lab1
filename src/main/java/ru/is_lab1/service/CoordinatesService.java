package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import ru.is_lab1.dto.request.CoordinatesRequest;
import ru.is_lab1.entity.Coordinates;
import ru.is_lab1.repository.CoordinatesRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CoordinatesService {
//    @Inject
//    CoordinatesRepository repository;
//
//    @Inject
//    ModelMapper modelMapper;
//
//    @Transactional
//    public Coordinates createCoordinates(CoordinatesRequest request){
//        Coordinates createdCoordinates = modelMapper.map(request, Coordinates.class);
//        return repository.save(createdCoordinates);
//    }
//
//    public Coordinates getCoordinatesById(Long id){
//        Optional<Coordinates> optionalCoordinates = repository.findById(id);
//        if (optionalCoordinates.isEmpty()){
//            throw new RuntimeException("Coordinates not found");
//        }
//        return optionalCoordinates.get();
//    }
//
//    public List<Coordinates> getPageCoordinates(int page, int size){
//        return repository.findPage(page, size);
//    }
//
//    @Transactional
//    public Coordinates updateCoordinates(Long id, CoordinatesRequest request){
//        Optional<Coordinates> optionalCoordinates = repository.findById(id);
//        if (optionalCoordinates.isEmpty()){
//            throw new RuntimeException("Coordinates not found");
//        }
//        Coordinates coordinates = optionalCoordinates.get();
//        modelMapper.map(request, coordinates);
//        return repository.update(coordinates);
//    }
//
//    @Transactional
//    public void deleteCoordinates(Long id){
//        if (!repository.delete(id)){
//            throw new RuntimeException("Coordinates not found");
//        }
//    }
//
}
