package com.api.kultur.controller;

import com.api.kultur.model.Coordinate;
import com.api.kultur.service.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Coordinate")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoordinateController {

    public final CoordinateService coordinateService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Coordinate> getList() {
        return coordinateService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Coordinate create(@RequestBody Coordinate coordinate) {
        return coordinateService.create(coordinate);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Coordinate getById(@PathVariable int id) {
        return coordinateService.getById(id);
    }
}
