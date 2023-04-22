package com.thomaspufahl.apiportfolio.PortfolioData.Person;

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
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<Person> add(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        Person person = new Person(firstname, lastname);
        manager.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> removeAll() {
        manager.deleteAll();
        return new ResponseEntity<>("Persons deleted", HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{person_id}")
    public ResponseEntity<String> removeById(@PathVariable Integer person_id) {
        manager.deleteById(person_id);
        return new ResponseEntity<>("Person deleted", HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{person_id}")
    public ResponseEntity<Optional<Person>> editById(
            @PathVariable Integer person_id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname
    ) {
        return new ResponseEntity<>(manager.editById(person_id, firstname, lastname), HttpStatus.OK);
    }

    @RequestMapping("**")
    public ResponseEntity<String> notFound() {
        return new ResponseEntity<>("This route not exists", HttpStatus.NOT_FOUND);
    }
}
