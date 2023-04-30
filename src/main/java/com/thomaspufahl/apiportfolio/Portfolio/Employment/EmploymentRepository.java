package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
    List<Employment> findAllByPerson(Person person);
}
