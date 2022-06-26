package com.api.kultur.service;

import com.api.kultur.model.Rating;
import com.api.kultur.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RatingService {
    private final RatingRepository ratingRepository;

    /**
     * get a rating by id
     * @param id the id of the rating
     * @return the rating
     */
    public Rating getById(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    /**
     * Create a new rating.
     * @param rating The rating to create.
     * @return the created and saved rating
     */
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }
}
