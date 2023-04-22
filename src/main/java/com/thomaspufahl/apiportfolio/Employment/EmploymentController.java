package com.thomaspufahl.apiportfolio.Employment;

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
//@PreAuthorize("hasAuthority('ADMIN')")
public class EmploymentController {

    private final EmploymentManager manager;

    @GetMapping
    public ResponseEntity<List<Employment>> getAll() {
        return new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{employment_id}")
    public ResponseEntity<Optional<Employment>> getById(@PathVariable Integer employment_id) {
        return new ResponseEntity<>(manager.getById(employment_id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employment> add(@RequestParam("name") String name, @RequestParam("description") String description) {
        Employment employment = new Employment(name, description);
        manager.save(employment);
        return new ResponseEntity<>(employment, HttpStatus.CREATED);
    }
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeAll() {
        manager.deleteAll();
        return new ResponseEntity<>("Employments deleted", HttpStatus.OK);
    }
    @DeleteMapping("/remove/{employment_id}")
    public ResponseEntity<String> removeById(@PathVariable Integer employment_id) {
        manager.deleteById(employment_id);
        return new ResponseEntity<>("Employment deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/{employment_id}")
    public ResponseEntity<Optional<Employment>> editById(
            @PathVariable Integer employment_id,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        return new ResponseEntity<>(manager.editById(employment_id, name, description), HttpStatus.OK);
    }


}