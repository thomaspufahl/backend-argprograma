package com.thomaspufahl.apiportfolio.Portfolio.Skill;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;

import java.util.List;
import java.util.Optional;

public interface SkillManager {

    List<Skill> all();
    List<Skill> allByPerson(Person person);
    Optional<Skill> getById(Integer skill_id);
    void create(Skill skill);
    Optional<Skill> editById(Integer skill_id, Skill skill);
    void delete();
    void deleteById(Integer skill_id);
}
