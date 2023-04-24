package com.thomaspufahl.apiportfolio.Portfolio.Person;

import com.thomaspufahl.apiportfolio.Security.model.User.User;

import java.util.List;
import java.util.Optional;

public interface PersonManager {
    List<Person> getAll();
    void save(Person person);
    void createRegWithUser(User user);
    void deleteAll();
    void deleteById(Integer id);
    Optional<Person> getById(Integer id);
    Optional<Person> editById(Integer id, Person person);
    Optional<Person> getByUserEmail(String email);

}
