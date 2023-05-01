package com.thomaspufahl.apiportfolio.Portfolio.Skill;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService implements SkillManager {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> all() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> allByPerson(Person person) {
        return skillRepository.findAllByPerson(person);
    }

    @Override
    public Optional<Skill> getById(Integer skill_id) {
        return skillRepository.findById(skill_id);
    }

    @Override
    public void create(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> editById(Integer skill_id, Skill skill) {
        if (skill.getName()!=null && skill.getName().length()>1) {
            getById(skill_id).orElseThrow().setName(skill.getName());
        }
        if (skill.getPercentage() >= 0) {
            getById(skill_id).orElseThrow().setPercentage(skill.getPercentage());
        }
        skillRepository.save(getById(skill_id).orElseThrow());
        return getById(skill_id);
    }

    @Override
    public void delete() {
        skillRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer skill_id) {
        skillRepository.deleteById(skill_id);
    }
}
