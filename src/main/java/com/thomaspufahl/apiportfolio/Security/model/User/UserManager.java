package com.thomaspufahl.apiportfolio.Security.model.User;


import java.util.List;
import java.util.Optional;

public interface UserManager {
    List<User> getAll();
    void deleteAll();
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
