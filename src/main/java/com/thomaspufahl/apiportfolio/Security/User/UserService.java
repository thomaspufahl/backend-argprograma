package com.thomaspufahl.apiportfolio.Security.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements UserManager{

    private final UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
    @Override
    public void deleteAll() { repository.deleteAll(); }
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
