package com.api.kultur.controller;

import com.api.kultur.model.Contact;
import com.api.kultur.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Contact")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactController {
    public final ContactService contactService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Contact> getList() {
        return contactService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Contact create(@RequestBody Contact contact) {
        return contactService.create(contact);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Contact getById(@PathVariable int id) {
        return contactService.getById(id);
    }

    @PutMapping(path = "id/{id}", name = "update", consumes = "application/json", produces = "application/json")
    public Contact update(@PathVariable int id, @RequestBody Contact contact) {
        return contactService.update(id, contact);
    }
}
