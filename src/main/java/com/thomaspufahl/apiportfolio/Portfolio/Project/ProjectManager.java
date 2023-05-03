package com.thomaspufahl.apiportfolio.Portfolio.Project;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;

import java.util.List;
import java.util.Optional;

public interface ProjectManager {
    List<Project> all();
    List<Project> allByPerson(Person person);
    Optional<Project> getById(Integer project_id);
    void create(Project project);
    Optional<Project> editById(Integer project_id, Project project);
    void delete();
    void deleteById(Integer project_id);
}
