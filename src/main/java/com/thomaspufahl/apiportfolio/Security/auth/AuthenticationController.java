package com.thomaspufahl.apiportfolio.Security.auth;

import com.thomaspufahl.apiportfolio.Security.User.UserService;
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
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody RegisterRequest request
    ) {
        if (userService.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("ERROR: This email is already in use", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
