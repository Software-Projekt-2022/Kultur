package com.api.kultur.service;

import com.api.kultur.model.Club;
import com.api.kultur.model.Contact;
import com.api.kultur.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClubService {
    private final ClubRepository clubRepository;
    private final ContactService contactService;

    /**
     * Fetches all clubs from the database.
     * @return a list of all clubs
     */
    public List<Club> getList() {
        return clubRepository.findAll();
    }

    /**
     * Create a new club and checks if the place already exists in the database. If it does, the place is linked to the existing one.
     * @param club The club to create.
     * @return the created and saved club.
     */
    public Club create(Club club) {
        Contact contact = club.getContact();
        if (contact.getId() != null){
            Contact contact1 = contactService.getById(contact.getId());
            if (contact.compareTo(contact1) != 0) {
                contact = contactService.create(contact);
            }
        } else {
            contact = contactService.create(contact);
        }
        club.setContact(contact);
        return clubRepository.save(club);
    }

    /**
     * Get a club by id.
     * @param id The id of the club.
     * @return The club with the given id.
     */
    public Club getById(int id) {
        return clubRepository.findById(id).orElse(null);
    }

    /**
     * Update a club and checks if the place already exists in the database. If it does, the place is linked to the existing one.
     * @param club The club to update.
     * @return the updated and saved club.
     */
    public Club update(int id, Club club) {
        club.setId(id);
        if (!clubRepository.findById(id).isPresent()) {
            return null;
        }
        return clubRepository.save(club);
    }
}
