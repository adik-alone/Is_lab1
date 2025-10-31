package ru.is_lab1.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import ru.is_lab1.dto.request.CoordinatesRequest;
import ru.is_lab1.entity.Coordinates;

@ApplicationScoped
public class CoordinatesMapper {

    public Coordinates toEntity(CoordinatesRequest request){
        if (request == null){
            return null;
        }
        Coordinates coordinates = new Coordinates();

        coordinates.setX(request.getX());
        coordinates.setY(request.getY());
        return coordinates;
    }

    public void updateEntity(CoordinatesRequest request, Coordinates coordinates){
        if (request == null || coordinates == null){
            return;
        }
        coordinates.setX(request.getX());
        coordinates.setY(request.getY());
    }
}
