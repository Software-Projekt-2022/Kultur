package com.api.kultur.repositories;

import com.api.kultur.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT e FROM Event e WHERE e.name = :name")
    Optional<Event> findByName(String name);

    @Query(value = "SELECT e FROM Event e ORDER BY e.date DESC")
    Optional<List<Event>> findByDateDesc();
}
