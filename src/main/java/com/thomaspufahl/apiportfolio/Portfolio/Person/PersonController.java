package com.thomaspufahl.apiportfolio.Portfolio.Person;

import com.thomaspufahl.apiportfolio.Tool.Storage.StorageManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonManager personManager;
    private final StorageManager storageManager;
    private final HttpServletRequest request;
    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(personManager.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{person_id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Integer person_id) {
        return new ResponseEntity<>(personManager.getById(person_id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/upload/avatar/{person_id}")
    public Map<String, String> uploadAvatar(
            @PathVariable Integer person_id,
            @RequestParam("avatar") MultipartFile multipartFile
    ) {
        String path = storageManager.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        personManager.editById(person_id, new Person(url, ""));
        return Map.of("url", url);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/upload/banner/{person_id}")
    public Map<String, String> uploadBanner(
            @PathVariable Integer person_id,
            @RequestParam("banner") MultipartFile multipartFile
    ) {
        String path = storageManager.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        personManager.editById(person_id, new Person("", url));
        return Map.of("url", url);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<Person> add(@RequestBody Person person) {
        personManager.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> removeAll() {
        personManager.deleteAll();
        return new ResponseEntity<>("Persons deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{person_id}")
    public ResponseEntity<String> removeById(@PathVariable Integer person_id) {
        personManager.deleteById(person_id);
        return new ResponseEntity<>("Person deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{person_id}")
    public ResponseEntity<Optional<Person>> editById(
            @PathVariable Integer person_id,
            @RequestBody Person person
    ) {
        return new ResponseEntity<>(personManager.editById(person_id, person), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/user/{user_email}")
    public ResponseEntity<Person> getByUserEmail(
            @PathVariable String user_email
    ) {
        return new ResponseEntity<>(personManager.getByUserEmail(user_email).orElseThrow(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PatchMapping("/user/{user_email}")
    public ResponseEntity<Person> editPersonByUserEmail(
            @PathVariable String user_email,
            @RequestBody Person person
    ) {
        return new ResponseEntity<>(personManager.editById(personManager.getByUserEmail(user_email).orElseThrow().getId(), person).orElseThrow(), HttpStatus.OK);
    }


    @RequestMapping("**")
    public ResponseEntity<String> notFound() {
        return new ResponseEntity<>("This route not exists", HttpStatus.NOT_FOUND);
    }
}
