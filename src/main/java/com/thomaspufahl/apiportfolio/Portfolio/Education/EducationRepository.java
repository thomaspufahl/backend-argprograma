package com.thomaspufahl.apiportfolio.Portfolio.Education;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    List<Education> findAllByPerson(Person person);
}
