package com.api.kultur.service;

import com.api.kultur.model.Coordinate;
import com.api.kultur.model.Event;
import com.api.kultur.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {
    private final EventRepository eventRepository;
    private final CoordinateService coordinateService;

    public List<Event> getList() {
        return eventRepository.findAll();
    }

    public Event create(Event event) {
        Coordinate place = event.getPlace();
        if (place.getLatitude() != null && place.getLongitude() != null) {
            Coordinate coordinate = coordinateService.getByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            if (coordinate == null){
                place = coordinateService.create(place);
            }
        }
        event.setPlace(place);
        return eventRepository.save(event);
    }

    public Event getById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    public Event getByTitle(String name) {
        Optional<Event> event = eventRepository.findByName(name);
        return event.orElse(null);
    }

    public List<Event> getNew() {
        Optional<List<Event>> event = eventRepository.findByDateDesc();
        return event.orElse(null);
    }

    public Event update(int id, Event event) {
        Coordinate place = event.getPlace();
        Coordinate coordinate;
        if (place.getId() != null) {
            coordinate = coordinateService.getById(place.getId());
            if (!Objects.equals(coordinate.getLatitude(), place.getLatitude()) || !Objects.equals(coordinate.getLongitude(), place.getLongitude())) {
                coordinate = coordinateService.create(place);
            }
        } else {
            coordinate = coordinateService.getByLongitudeAndLatitude(place.getLongitude(), place.getLatitude());
            if (coordinate == null) {
                coordinate = coordinateService.create(place);
            }
        }
        event.setPlace(coordinate);
        return eventRepository.save(event);
    }
}
