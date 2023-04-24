package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmploymentService implements EmploymentManager {
    private final EmploymentRepository repository;
    @Override
    public List<Employment> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(Employment employment) {
        repository.save(employment);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Employment> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Employment> editById(Integer id, String name, String description) {
        getById(id).orElseThrow().setName(name);
        getById(id).orElseThrow().setDescription(description);
        repository.save(getById(id).orElseThrow());
        return getById(id);
    }

    @Override
    public boolean existById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Employment> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean existByName(String name) {
        return repository.existsByName(name);
    }



}
