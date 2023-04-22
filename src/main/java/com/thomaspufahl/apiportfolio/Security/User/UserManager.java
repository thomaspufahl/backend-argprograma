package com.thomaspufahl.apiportfolio.Security.User;

import java.util.List;

public interface UserManager {
    List<User> getAll();
    void deleteAll();
    boolean existsByEmail(String email);
}
