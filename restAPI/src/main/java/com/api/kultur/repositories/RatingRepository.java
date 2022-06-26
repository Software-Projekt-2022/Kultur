package com.api.kultur.repositories;

import com.api.kultur.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("SELECT r FROM Rating r WHERE r.stars = ?1")
    Optional<Rating> findByStars(Integer stars);
}
