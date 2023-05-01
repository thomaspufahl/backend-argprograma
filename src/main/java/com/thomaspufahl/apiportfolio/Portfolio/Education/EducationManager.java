package com.thomaspufahl.apiportfolio.Portfolio.Education;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;

import java.util.List;
import java.util.Optional;

public interface EducationManager {
    List<Education> all();
    List<Education> allByPerson(Person person);
    Optional<Education> getById(Integer education_id);
    void create(Education education);
    Optional<Education> editById(Integer education_id, Education education);
    void delete();
    void deleteById(Integer education_id);
}
