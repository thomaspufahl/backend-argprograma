package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentManager employmentManager;
    @GetMapping
    public ResponseEntity<List<Employment>> getAll() {
        return new ResponseEntity<>(employmentManager.all(), HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Employment>> getAllByPerson(@PathVariable Integer person_id) {
        Person person = new Person();
        person.setId(person_id);
        return new ResponseEntity<>(employmentManager.allByPerson(person), HttpStatus.OK);
    }

    @GetMapping("/{employmen_id}")
    public ResponseEntity<Optional<Employment>> getById(@PathVariable Integer employmen_id) {
        return new ResponseEntity<>(employmentManager.getById(employmen_id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<String> create(
            @RequestBody Employment employment
    ) {
        employmentManager.create(employment);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{employment_id}")
    public ResponseEntity<Optional<Employment>> edit(
            @PathVariable Integer employment_id,
            @RequestBody Employment employment
    ) {
        return new ResponseEntity<>(employmentManager.editById(employment_id, employment), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{employment_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer employment_id
    ) {
        employmentManager.deleteById(employment_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        employmentManager.delete();
        return new ResponseEntity<>("All employments deleted", HttpStatus.OK);
    }

}
