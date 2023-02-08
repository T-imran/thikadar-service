package com.example.task.controller;


import com.example.task.auth.AuthenticateRequest;
import com.example.task.auth.AuthenticationResponse;
import com.example.task.auth.AuthenticationService;
import com.example.task.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request) {
        AuthenticationResponse tokenResponse = new AuthenticationResponse();
        tokenResponse.setToken(authenticationService.authenticate(request));
        return ResponseEntity.ok(tokenResponse);

    }
}

