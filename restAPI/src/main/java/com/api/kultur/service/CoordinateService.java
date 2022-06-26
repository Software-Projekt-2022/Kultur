package com.api.kultur.service;


import com.api.kultur.model.Coordinate;
import com.api.kultur.repositories.CoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;

    public List<Coordinate> getList() {
        return coordinateRepository.findAll();
    }

    public Coordinate create(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }

    public Coordinate getById(int id) {
        Optional<Coordinate> coordinate = coordinateRepository.findById(id);
        return coordinate.orElse(null);
    }

    public Coordinate getByLongitudeAndLatitude(double longitude, double latitude) {
        Optional<Coordinate> coordinate = coordinateRepository.findByLongitudeAndLatitude(longitude, latitude);
        return coordinate.orElse(null);
    }
}
