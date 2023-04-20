package com.thomaspufahl.apiportfolio.Person;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonManager{

    private final static Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository repository;
    @Override
    public List<Person> getPersons() {
        logger.info("Sending list of persons...");
        return repository.findAll();
    }

    @Override
    public void savePerson(Person person) {
        repository.save(person);
        logger.info("Person has been saved");
    }

    @Override
    public void deletePersonById(Integer id) {
        repository.deleteById(id);
        logger.info("Person has been deleted");
    }

    @Override
    public Person findPersonById(Integer id) {
        logger.info("Finding person...");
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Person editPersonById(Integer id, String firstname, String lastname) {
        logger.info("Modifying person...");
        findPersonById(id).setFirstname(firstname);
        findPersonById(id).setLastname(lastname);
        return findPersonById(id);
    }
}
