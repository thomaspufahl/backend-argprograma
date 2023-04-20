package com.thomaspufahl.apiportfolio.Person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonManager manager;
    @GetMapping
    public List<Person> getPersons() {return manager.getPersons();}

    @GetMapping("person/{person_id}")
    public Person getPersonById(@PathVariable Integer person_id) {
        return manager.findPersonById(person_id);
    }

    @PostMapping("add")
    public ResponseEntity<Person> addPerson(Person person) {
        manager.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @DeleteMapping("remove/{person_id}")
    public ResponseEntity<?> removePerson(@PathVariable Integer person_id) {
        manager.deletePersonById(person_id);
        return new ResponseEntity<>("Person deleted", HttpStatus.OK);
    }

    @PutMapping("edit/{person_id}")
    public Person editPerson(
            @PathVariable Integer person_id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname
    ) {
        return manager.editPersonById(person_id, firstname, lastname);
    }
}
