package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import com.thomaspufahl.apiportfolio.Portfolio.Responsibility.ResponsibiltyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmploymentService implements EmploymentManager{

    private final EmploymentRepository employmentRepository;
    private final ResponsibiltyManager responsibiltyManager;

    @Override
    public List<Employment> all() {
        return employmentRepository.findAll();
    }

    @Override
    public List<Employment> allByPerson(Person person) {
        return employmentRepository.findAllByPerson(person);
    }

    @Override
    public Optional<Employment> getById(Integer employment_id) {
        return employmentRepository.findById(employment_id);
    }

    @Override
    public void create(Employment employment) {
        employmentRepository.save(employment);
    }

    @Override
    public Optional<Employment> editById(Integer employment_id, Employment employment) {
        if (employment.getPosition()!=null && employment.getPosition().length()>1) {
            getById(employment_id).orElseThrow().setPosition(employment.getPosition());
        }
        if (employment.getEmployeer()!=null && employment.getEmployeer().length()>1) {
            getById(employment_id).orElseThrow().setEmployeer(employment.getEmployeer());
        }
        if (employment.getStart()!=null) {
            getById(employment_id).orElseThrow().setStart(employment.getStart());
        }
        if (employment.getEnd()!=null) {
            getById(employment_id).orElseThrow().setEnd(employment.getEnd());
        }
        employmentRepository.save(getById(employment_id).orElseThrow());
        return getById(employment_id);
    }

    @Override
    public void delete() {
        employmentRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer employment_id) {
        responsibiltyManager.deleteAllByEmployment(getById(employment_id).orElseThrow());
        employmentRepository.deleteById(employment_id);
    }

}
