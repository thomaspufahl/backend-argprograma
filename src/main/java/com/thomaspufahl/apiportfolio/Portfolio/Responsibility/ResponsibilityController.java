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

    private final ResponsibilityManager responsibilityManager;

    @GetMapping
    public ResponseEntity<List<Responsibility>> getAll() {
        return new ResponseEntity<>(responsibilityManager.all(), HttpStatus.OK);
    }

    @GetMapping("/employment/{employment_id}")
    public ResponseEntity<List<Responsibility>> getAllByEmployment(@PathVariable Integer employment_id) {
        Employment employment = new Employment();
        employment.setId(employment_id);
        return new ResponseEntity<>(responsibilityManager.allByEmployment(employment), HttpStatus.OK);
    }

    @GetMapping("/{responsibility_id}")
    public ResponseEntity<Optional<Responsibility>> getById(@PathVariable Integer responsibility_id) {
        return new ResponseEntity<>(responsibilityManager.getById(responsibility_id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add/{employment_id}")
    public ResponseEntity<String> create(
            @PathVariable Integer employment_id,
            @RequestBody Responsibility responsibility
    ) {
        responsibility.setEmployment(new Employment(employment_id));
        responsibilityManager.create(responsibility);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{responsibility_id}")
    public ResponseEntity<Optional<Responsibility>> editById(
            @PathVariable Integer responsibility_id,
            @RequestBody Responsibility responsibility
    ) {
        return new ResponseEntity<>(responsibilityManager.editById(responsibility_id, responsibility), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{responsibility_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer responsibility_id
    ) {
        responsibilityManager.deleteById(responsibility_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        responsibilityManager.delete();
        return new ResponseEntity<>("All responsabilities deleted", HttpStatus.OK);
    }
}
