package com.thomaspufahl.apiportfolio.Portfolio.Skill;

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
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {
    private final SkillManager skillManager;

    @GetMapping
    public ResponseEntity<List<Skill>> getAll() {
        return new ResponseEntity<>(skillManager.all(), HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Skill>> getAllByPerson(@PathVariable Integer person_id) {
        Person person = new Person();
        person.setId(person_id);
        return new ResponseEntity<>(skillManager.allByPerson(person), HttpStatus.OK);
    }

    @GetMapping("/{skill_id}")
    public ResponseEntity<Optional<Skill>> getById(@PathVariable Integer skill_id) {
        return new ResponseEntity<>(skillManager.getById(skill_id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<String> create(
            @RequestBody Skill skill
    ) {
        skillManager.create(skill);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{skill_id}")
    public ResponseEntity<Optional<Skill>> edit(
            @PathVariable Integer skill_id,
            @RequestBody Skill skill
    ) {
        return new ResponseEntity<>(skillManager.editById(skill_id, skill), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{skill_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer skill_id
    ) {
        skillManager.deleteById(skill_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        skillManager.delete();
        return new ResponseEntity<>("All skills deleted", HttpStatus.OK);
    }
}
