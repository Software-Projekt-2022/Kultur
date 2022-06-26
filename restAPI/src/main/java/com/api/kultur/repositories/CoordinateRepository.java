package com.api.kultur.repositories;

import com.api.kultur.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {

    @Query("SELECT c FROM Coordinate c WHERE c.longitude = :longitude AND c.latitude = :latitude")
    Optional<Coordinate> findByLongitudeAndLatitude(double longitude, double latitude);
}
