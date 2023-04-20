package com.thomaspufahl.apiportfolio.Person;

import java.util.List;

public interface PersonManager {
    List<Person> getPersons();
    void savePerson(Person person);
    void deletePersonById(Integer id);
    Person findPersonById(Integer id);
    Person editPersonById(Integer id, String firstname, String lastname);
}
