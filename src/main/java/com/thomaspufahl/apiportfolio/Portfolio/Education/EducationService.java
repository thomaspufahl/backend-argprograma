package com.thomaspufahl.apiportfolio.Portfolio.Education;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationService implements EducationManager {

    private final EducationRepository educationRepository;

    @Override
    public List<Education> all() {
        return educationRepository.findAll();
    }

    @Override
    public List<Education> allByPerson(Person person) {
        return educationRepository.findAllByPerson(person);
    }

    @Override
    public Optional<Education> getById(Integer education_id) {
        return educationRepository.findById(education_id);
    }

    @Override
    public void create(Education education) {
        educationRepository.save(education);
    }

    @Override
    public Optional<Education> editById(Integer education_id, Education education) {
        if (education.getDegree()!=null && education.getDegree().length()>1) {
            getById(education_id).orElseThrow().setDegree(education.getDegree());
        }
        if (education.getSchool()!=null && education.getSchool().length()>1) {
            getById(education_id).orElseThrow().setSchool(education.getSchool());
        }
        if (education.getStart()!=null) {
            getById(education_id).orElseThrow().setStart(education.getStart());
        }
        if (education.getEnd()!=null) {
            getById(education_id).orElseThrow().setEnd(education.getEnd());
        }
        if (education.getDescription()!=null && education.getDescription().length()>1) {
            getById(education_id).orElseThrow().setDescription(education.getDescription());
        }
        educationRepository.save(getById(education_id).orElseThrow());
        return getById(education_id);
    }

    @Override
    public void delete() {
        educationRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer education_id) {
        educationRepository.deleteById(education_id);
    }
}
