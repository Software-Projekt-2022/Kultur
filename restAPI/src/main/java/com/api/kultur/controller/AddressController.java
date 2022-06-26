package com.api.kultur.controller;

import com.api.kultur.model.Address;
import com.api.kultur.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Address")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {
    public final AddressService addressService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Address> getList() {
        return addressService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Address create(@RequestBody Address address) {
        return addressService.create(address);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Address getById(@PathVariable int id) {
        return addressService.getById(id);
    }

    @PutMapping(path = "id/{id}", name = "update", consumes = "application/json", produces = "application/json")
    public Address update(@PathVariable int id, @RequestBody Address address) {
        return addressService.update(id, address);
    }
}
