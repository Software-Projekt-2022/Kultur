package com.api.kultur.service;


import com.api.kultur.model.User;
import com.api.kultur.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;

    public List<User> getList() {
        return userRepository.findAll();
    }

    public User create(User user) {

        return userRepository.save(user);
    }

    public User getById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User update(int id, User user) {
        User user1 = user;
        user1.setId(id);
        return userRepository.save(user1);
    }
}
