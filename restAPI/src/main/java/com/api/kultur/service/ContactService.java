package com.api.kultur.service;

import com.api.kultur.model.Address;
import com.api.kultur.model.Contact;
import com.api.kultur.model.Rating;
import com.api.kultur.repositories.AddressRepository;
import com.api.kultur.repositories.ContactRepository;
import com.api.kultur.repositories.CoordinateRepository;
import com.api.kultur.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContactService {
    private final ContactRepository contactRepository;
    private final AddressService addressService;
    private final RatingService ratingService;

    /**
     * gets all contacts
     * @return a list of contacts
     */
    public List<Contact> getList() {
        return contactRepository.findAll();
    }

    /**
     * Create a new contact and checks if the address and coordinate already exist.
     * @param contact The contact to create.
     * @return the created and saved contact
     */
    public Contact create(Contact contact) {
        Address address = contact.getAddress();
        if (address.getId() != null && address.getId() != 0) {
            Address address1 = addressService.getById(address.getId());
            if (address.compareTo(address1) != 0) {
                address = addressService.create(address);
            }
        } else {
            address = addressService.create(address);
        }
        contact.setAddress(address);
        Set<Rating> ratings = contact.getRating();
        if (ratings != null) {
            for (Rating rating : ratings){
                if (rating != null) {
                    if (rating.getId() != null && rating.getId() > 0) {
                        Rating rating1 = ratingService.getById(rating.getId());
                        if (rating1 != null) {
                            if (rating.compareTo(rating1) != 0) {
                                rating = ratingService.create(rating);
                            }
                        } else {
                            rating = ratingService.create(rating);
                        }
                    } else {
                        rating = ratingService.create(rating);
                    }
                    contact.addRating(rating);
                }
            }
        }
        return contactRepository.save(contact);
    }

    /**
     * Get a contact by id.
     * @param id The id of the contact.
     * @return The contact with the given id.
     */
    public Contact getById(int id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.orElse(null);
    }

    /**
     * Update a contact and checks if the address and coordinate already exist.
     *
     * @param id
     * @param contact The contact to update.
     * @return the updated and saved contact
     */
    public Contact update(int id, Contact contact) {
        Contact contact1 = getById(id);
        if (contact1 != null) {
            contact1.setAddress(contact.getAddress());
            contact1.setCategory(contact.getCategory());
            contact1.setDescription(contact.getDescription());
            contact1.setHours(contact.getHours());
            contact1.setImage(contact.getImage());
            contact1.setName(contact.getName());
            contact1.setPhone(contact.getPhone());
            contact1.setWebsite(contact.getWebsite());
            return contactRepository.save(contact1);
        }
        return null;
    }
}
