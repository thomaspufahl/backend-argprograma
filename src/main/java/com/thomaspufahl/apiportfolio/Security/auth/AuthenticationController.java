package com.thomaspufahl.apiportfolio.Security.auth;

import com.thomaspufahl.apiportfolio.Tool.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final EntityManager entityManager;
    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody RegisterRequest request
    ) {
        if (entityManager.getUserManager().existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("ERROR: This email is already in use", HttpStatus.BAD_REQUEST);
        }
        AuthenticationResponse token = authService.register(request);
        entityManager.getPersonManager().createRegWithUser(entityManager.getUserManager().findByEmail(request.getEmail()).orElseThrow());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
