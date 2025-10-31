package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.dto.request.LocationRequest;
import ru.is_lab1.entity.Location;
import ru.is_lab1.service.LocationService;

@ApplicationScoped
public class LocationMapper {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    public Location toEntity(LocationRequest locationRequest) {
        if (locationRequest == null) {
            return null;
        }
        Location location = new Location();
        location.setX(locationRequest.getX());
        location.setY(locationRequest.getY());
        location.setZ(locationRequest.getZ());
        return location;
    }
    public void map(LocationRequest locationRequest, Location location) {
        logger.info("Updating Location in Mapper");
        if (locationRequest == null || location == null) {
            return;
        }
        logger.info("Started location: " + location);
        location.setX(locationRequest.getX());
        location.setY(locationRequest.getY());
        location.setZ(locationRequest.getZ());
        logger.info("Finish location: " + location);
    }
}
