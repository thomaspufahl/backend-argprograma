package com.thomaspufahl.apiportfolio.Security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }
}
