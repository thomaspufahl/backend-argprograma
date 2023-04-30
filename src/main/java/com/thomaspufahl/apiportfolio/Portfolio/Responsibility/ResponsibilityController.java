package com.thomaspufahl.apiportfolio.Portfolio.Responsibility;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/responsibility")
@RequiredArgsConstructor
public class ResponsibilityController {

    private final ResponsibiltyManager responsibiltyManager;

    @GetMapping
    public ResponseEntity<List<Responsibility>> getAll() {
        return new ResponseEntity<>(responsibiltyManager.all(), HttpStatus.OK);
    }

    @GetMapping("/employment/{employment_id}")
    public ResponseEntity<List<Responsibility>> getAllByEmployment(@PathVariable Integer employment_id) {
        Employment employment = new Employment();
        employment.setId(employment_id);
        return new ResponseEntity<>(responsibiltyManager.allByEmployment(employment), HttpStatus.OK);
    }

    @GetMapping("/{responsibility_id}")
    public ResponseEntity<Optional<Responsibility>> getById(@PathVariable Integer responsibility_id) {
        return new ResponseEntity<>(responsibiltyManager.getById(responsibility_id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add/{employment_id}")
    public ResponseEntity<String> create(
            @PathVariable Integer employment_id,
            @RequestBody Responsibility responsibility
    ) {
        responsibility.setEmployment(new Employment(employment_id));
        responsibiltyManager.create(responsibility);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{responsibility_id}")
    public ResponseEntity<Optional<Responsibility>> editById(
            @PathVariable Integer responsibility_id,
            @RequestBody Responsibility responsibility
    ) {
        return new ResponseEntity<>(responsibiltyManager.editById(responsibility_id, responsibility), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{responsibility_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer responsibility_id
    ) {
        responsibiltyManager.deleteById(responsibility_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        responsibiltyManager.delete();
        return new ResponseEntity<>("All responsabilities deleted", HttpStatus.OK);
    }
}
