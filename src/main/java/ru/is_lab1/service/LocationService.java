package ru.is_lab1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.LocationRequest;
import ru.is_lab1.entity.Location;
//import ru.is_lab1.mapper.LocationMapper;
import ru.is_lab1.mapper.LocationMapper;
import ru.is_lab1.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Inject
    private LocationRepository repository;

    @Inject
//    private ModelMapper modelMapper;
    private LocationMapper mapper;


    @Transactional
    public Location createLocation(LocationRequest request){
//        Location location = modelMapper.map(request, Location.class);
        Location location = mapper.toEntity(request);
        return repository.save(location);
    }

    public Location getLocationById(Long id){
        Optional<Location> location = repository.findById(id);
        if (location.isEmpty()){
            throw new RuntimeException("Location not found");
        }
        return location.get();
    }
    public List<Location> getPageLocation(int page, int size){
        return repository.findPage(page, size);
    }

    @Transactional
    public Location updateLocation(Long id, LocationRequest request){
        logger.info("Обновление локации");
        Optional<Location> optionalLocation = repository.findById(id);
        if (optionalLocation.isEmpty()){
            throw new RuntimeException("Location not found");
        }
        Location location = optionalLocation.get();
        logger.info("Локация до обновления" + location);
//        modelMapper.map(request, location);
        mapper.map(request, location);

        logger.info("Локация с обновленными полями" + location);

        return repository.update(location);
    }
    @Transactional
    public void deleteLocation(Long id){
        logger.info("LocationService.deleteLocation");
        if (!repository.delete(id)){
            throw new RuntimeException("Location not found");
        }
    }

    public List<Location> getAllLocation() {
        return repository.findAll();
    }
}
