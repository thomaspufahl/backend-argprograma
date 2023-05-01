package com.thomaspufahl.apiportfolio.Portfolio.Education;

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
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationManager educationManager;
    @GetMapping
    public ResponseEntity<List<Education>> getAll() {
        return new ResponseEntity<>(educationManager.all(), HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Education>> getAllByPerson(@PathVariable Integer person_id) {
        Person person = new Person();
        person.setId(person_id);
        return new ResponseEntity<>(educationManager.allByPerson(person), HttpStatus.OK);
    }

    @GetMapping("/{education_id}")
    public ResponseEntity<Optional<Education>> getById(@PathVariable Integer education_id) {
        return new ResponseEntity<>(educationManager.getById(education_id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<String> create(
            @RequestBody Education education
    ) {
        educationManager.create(education);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{education_id}")
    public ResponseEntity<Optional<Education>> edit(
            @PathVariable Integer education_id,
            @RequestBody Education education
    ) {
        return new ResponseEntity<>(educationManager.editById(education_id, education), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{education_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer education_id
    ) {
        educationManager.deleteById(education_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        educationManager.delete();
        return new ResponseEntity<>("All educations deleted", HttpStatus.OK);
    }
}
