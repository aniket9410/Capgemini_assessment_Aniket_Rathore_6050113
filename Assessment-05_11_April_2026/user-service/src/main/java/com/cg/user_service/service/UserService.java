package com.cg.user_service.service;  

import org.springframework.stereotype.Service;
import com.cg.user_service.repository.UserRepository;
import com.cg.user_service.entity.User;
import com.cg.user_service.exception.UserNotFoundException;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}