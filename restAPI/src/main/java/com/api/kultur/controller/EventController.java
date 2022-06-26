package com.api.kultur.controller;

import com.api.kultur.model.Event;
import com.api.kultur.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Event")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {
    public final EventService eventService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Event> getList() {
        return eventService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Event create(@RequestBody Event event) {
        return eventService.create(event);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Event getById(@PathVariable int id) {
        return eventService.getById(id);
    }

    @GetMapping(path = "title/{title}", name = "getByTitle", produces = "application/json")
    public Event getByTitle(@PathVariable String title) {
        return eventService.getByTitle(title);
    }

    @GetMapping(path = "new", name = "getNew", produces = "application/json")
    public List<Event> getNew() {
        return eventService.getNew();
    }
}
