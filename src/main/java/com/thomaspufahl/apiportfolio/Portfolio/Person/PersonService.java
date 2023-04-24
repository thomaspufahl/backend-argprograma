package com.thomaspufahl.apiportfolio.Portfolio.Person;

import com.thomaspufahl.apiportfolio.Security.model.User.User;
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
    private final PersonRepository personRepository;
    @Override
    public List<Person> getAll() {
        logger.info("Sending list of persons...");
        return personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
        logger.info("Person has been saved");
    }

    @Override
    public void createRegWithUser(User user) {
        personRepository.save(new Person(user));
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
        logger.info("Persons has been deleted");
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
        logger.info("Person has been deleted");
    }

    @Override
    public Optional<Person> getById(Integer id) {
        logger.info("Finding person...");
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> editById(Integer id, Person person) {
        logger.info("Modifying person...");
        if (person.getFirstname()!=null && person.getFirstname().length()>1) {
            getById(id).orElseThrow().setFirstname(person.getFirstname());
        }
        if (person.getLastname()!=null && person.getLastname().length()>1) {
            getById(id).orElseThrow().setLastname(person.getLastname());
        }
        if (person.getDescription()!=null && person.getDescription().length()>1) {
            getById(id).orElseThrow().setDescription(person.getDescription());
        }
        personRepository.save(getById(id).orElseThrow());
        return getById(id);
    }

    @Override
    public Optional<Person> getByUserEmail(String email) {
        return personRepository.getByUserEmail(email);
    }

}
