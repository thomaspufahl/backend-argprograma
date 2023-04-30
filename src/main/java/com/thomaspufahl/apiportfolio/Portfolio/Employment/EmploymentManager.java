package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;

import java.util.List;
import java.util.Optional;

public interface EmploymentManager {
    List<Employment> all();
    List<Employment> allByPerson(Person person);
    Optional<Employment> getById(Integer employment_id);
    void create(Employment employment);
    Optional<Employment> editById(Integer employment_id, Employment employment);
    void delete();
    void deleteById(Integer employment_id);
}
