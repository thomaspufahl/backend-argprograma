package com.thomaspufahl.apiportfolio.Security.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserManager manager;
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> removeAll() {
        manager.deleteAll();
        return new ResponseEntity<>("Users deleted", HttpStatus.OK);
    }

}
