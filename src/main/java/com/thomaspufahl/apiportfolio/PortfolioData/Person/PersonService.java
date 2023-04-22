package com.thomaspufahl.apiportfolio.PortfolioData.Person;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonManager{

    private final static Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository repository;
    @Override
    public List<Person> getAll() {
        logger.info("Sending list of persons...");
        return repository.findAll();
    }

    @Override
    public void save(Person person) {
        repository.save(person);
        logger.info("Person has been saved");
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
        logger.info("Persons has been deleted");
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
        logger.info("Person has been deleted");
    }

    @Override
    public Optional<Person> getById(Integer id) {
        logger.info("Finding person...");
        return repository.findById(id);
    }

    @Override
    public Optional<Person> editById(Integer id, Person person) {
        logger.info("Modifying person...");
        getById(id).orElseThrow().setFirstname(person.getFirstname());
        getById(id).orElseThrow().setLastname(person.getLastname());
        getById(id).orElseThrow().setDescription(person.getDescription());
        repository.save(getById(id).orElseThrow());
        return getById(id);
    }




}
