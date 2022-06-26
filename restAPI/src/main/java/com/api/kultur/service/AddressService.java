package com.api.kultur.service;

import com.api.kultur.model.Address;
import com.api.kultur.model.Coordinate;
import com.api.kultur.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {
    private final AddressRepository addressRepository;
    private final CoordinateService coordinateService;

    /**
     * Fetches all addresses from the database.
     * @return a list of all addresses
     */
    public List<Address> getList() {
        return addressRepository.findAll();
    }

    /**
     * Create a new address and checks if the address and coordinate already exist.
     * @param address The address to create.
     * @return the created and saved address
     */
    public Address create(Address address) {
        Coordinate coordinate = address.getCoordinates();
        if (coordinate.getLatitude() != null && coordinate.getLongitude() != null) {
            Coordinate coordinate1 = coordinateService.getByLongitudeAndLatitude(coordinate.getLongitude(), coordinate.getLatitude());
            if (coordinate1 == null) {
                coordinate = coordinateService.create(coordinate);
            }
        } else {
            coordinate = coordinateService.create(coordinate);
        }
        address.setCoordinates(coordinate);
        return addressRepository.save(address);
    }

    /**
     * Get an address by id.
     * @param id The id of the address.
     * @return The address with the given id.
     */
    public Address getById(int id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }

    /**
     * Update an address and checks if the address and coordinate already exist.
     * @param id
     * @param address
     * @return
     */
    public Address update(int id, Address address) {
        Address address1 = getById(id);
        if (address1 != null){
            address1.setStreet(address.getStreet());
            address1.setNumber(address.getNumber());
            address1.setZip(address.getZip());
            address1.setCity(address.getCity());
            address1.setCoordinates(address.getCoordinates());
            return addressRepository.save(address1);
        }
        return null;
    }
}
