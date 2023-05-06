package com.thomaspufahl.apiportfolio.Portfolio.Project;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
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
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectManager projectManager;
    private final StorageManager storageManager;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return new ResponseEntity<>(projectManager.all(), HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Project>> getAllByPerson(@PathVariable Integer person_id) {
        Person person = new Person();
        person.setId(person_id);
        return new ResponseEntity<>(projectManager.allByPerson(person), HttpStatus.OK);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Optional<Project>> getById(@PathVariable Integer project_id) {
        return new ResponseEntity<>(projectManager.getById(project_id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/upload/{project_id}")
    public Map<String, String> upload(
            @PathVariable Integer project_id,
            @RequestParam("image") MultipartFile multipartFile
    ) {
        String path = storageManager.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        System.out.println("--------------------");
        System.out.println(url);
        System.out.println("--------------------");
        projectManager.editById(project_id, new Project(url));
        return Map.of("url", url);
    }


    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/modify/add")
    public ResponseEntity<String> create(
            @RequestBody Project project
    ) {
        projectManager.create(project);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/modify/edit/{project_id}")
    public ResponseEntity<Optional<Project>> edit(
            @PathVariable Integer project_id,
            @RequestBody Project project
    ) {
        return new ResponseEntity<>(projectManager.editById(project_id, project), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/modify/remove/{project_id}")
    public ResponseEntity<String> edit(
            @PathVariable Integer project_id
    ) {
        projectManager.deleteById(project_id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/modify/admin/remove")
    public ResponseEntity<String> delete() {
        projectManager.delete();
        return new ResponseEntity<>("All projects deleted", HttpStatus.OK);
    }
}
