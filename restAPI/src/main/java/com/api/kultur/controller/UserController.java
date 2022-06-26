package com.api.kultur.controller;


import com.api.kultur.model.User;
import com.api.kultur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    public final UserService userService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<User> getList() {
        return userService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PutMapping(path = "id/{id}", name = "update", consumes = "application/json", produces = "application/json")
    public User update(@PathVariable int id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
