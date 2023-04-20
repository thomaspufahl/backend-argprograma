package com.thomaspufahl.apiportfolio.Person;

import java.util.List;

public interface PersonManager {
    public List<Person> getPersons();
    public void savePerson(Person person);
    public void deletePersonById(Integer id);
    public Person findPersonById(Integer id);
}
