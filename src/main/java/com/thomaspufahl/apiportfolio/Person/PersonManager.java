package com.thomaspufahl.apiportfolio.Person;

import java.util.List;
import java.util.Optional;

public interface PersonManager {
    List<Person> getAll();
    void save(Person person);
    void deleteAll();
    void deleteById(Integer id);
    Optional<Person> getById(Integer id);
    Optional<Person> editById(Integer id, String firstname, String lastname);
}
