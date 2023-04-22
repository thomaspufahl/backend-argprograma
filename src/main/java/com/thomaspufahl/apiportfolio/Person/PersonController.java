package com.thomaspufahl.apiportfolio.Person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
public class PersonController {
    private final PersonManager manager;
    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{person_id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Integer person_id) {
        return new ResponseEntity<>(manager.getById(person_id), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Person> add(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        Person person = new Person(firstname, lastname);
        manager.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeAll() {
        manager.deleteAll();
        return new ResponseEntity<>("Persons deleted", HttpStatus.OK);
    }
    @DeleteMapping("/remove/{person_id}")
    public ResponseEntity<String> removeById(@PathVariable Integer person_id) {
        manager.deleteById(person_id);
        return new ResponseEntity<>("Person deleted", HttpStatus.OK);
    }
    @PutMapping("/edit/{person_id}")
    public ResponseEntity<Optional<Person>> editById(
            @PathVariable Integer person_id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname
    ) {
        return new ResponseEntity<>(manager.editById(person_id, firstname, lastname), HttpStatus.OK);
    }
}
