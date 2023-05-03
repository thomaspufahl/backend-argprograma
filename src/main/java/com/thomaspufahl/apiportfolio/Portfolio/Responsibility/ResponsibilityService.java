package com.thomaspufahl.apiportfolio.Portfolio.Responsibility;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResponsibilityService implements ResponsibilityManager {

    private final ResponsibilityRepository responsibilityRepository;

    @Override
    public List<Responsibility> all() {
        return responsibilityRepository.findAll();
    }

    @Override
    public List<Responsibility> allByEmployment(Employment employment) {
        return responsibilityRepository.findAllByEmployment(employment);
    }

    @Override
    public Optional<Responsibility> getById(Integer responsibility_id) {
        return responsibilityRepository.findById(responsibility_id);
    }

    @Override
    public void create(Responsibility responsibility) {
        responsibilityRepository.save(responsibility);
    }

    @Override
    public Optional<Responsibility> editById(Integer responsibility_id, Responsibility responsibility) {
        if (responsibility.getDescription()!=null && responsibility.getDescription().length()>1) {
            getById(responsibility_id).orElseThrow().setDescription(responsibility.getDescription());
        }
        responsibilityRepository.save(getById(responsibility_id).orElseThrow());
        return getById(responsibility_id);
    }

    @Override
    public void delete() {
        responsibilityRepository.deleteAll();
    }

    @Override
    public void deleteById(Integer responsibility_id) {
        responsibilityRepository.deleteById(responsibility_id);
    }

    @Override
    public void deleteAllByEmployment(Employment employment) {
        responsibilityRepository.deleteAllByEmployment(employment);
    }
}
