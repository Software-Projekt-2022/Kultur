package com.api.kultur.controller;

import com.api.kultur.model.Club;
import com.api.kultur.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Club")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClubController {
    public final ClubService clubService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Club> getList() {
        return clubService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Club create(@RequestBody Club club) {
        return clubService.create(club);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Club getById(@PathVariable int id) {
        return clubService.getById(id);
    }

    @PutMapping(path = "id/{id}", name = "update", consumes = "application/json", produces = "application/json")
    public Club update(@PathVariable int id, @RequestBody Club club) {
        return clubService.update(id, club);
    }
}
