package com.example.task.controller;


import com.example.task.auth.AuthenticateRequest;
import com.example.task.auth.AuthenticationResponse;
import com.example.task.auth.AuthenticationService;
import com.example.task.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authentication(@RequestBody AuthenticateRequest request) {
        return authenticationService.authenticate(request);

    }

    @GetMapping("/test-render")
    public ResponseEntity<?> testRender(){
        try {
            Map<String, String> test = new HashMap<>();
            test.put("myApp", "running");
            test.put("status", "Okay");
            test.put("service", "springBoot");
            return new ResponseEntity<>(test, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }
}




