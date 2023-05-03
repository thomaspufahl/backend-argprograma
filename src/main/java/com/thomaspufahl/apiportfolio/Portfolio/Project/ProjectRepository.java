package com.thomaspufahl.apiportfolio.Portfolio.Project;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByPerson(Person person);
}
