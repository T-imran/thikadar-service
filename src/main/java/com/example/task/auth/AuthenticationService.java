package com.example.task.auth;

import com.example.task.exception.UserExceptions;
import com.example.task.model.Role;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import com.example.task.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRole().equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        }
        if (request.getRole().equals("MANAGER")) {
            user.setRole(Role.MANAGER);
        }
        if (request.getRole().equals("USER")) {
            user.setRole(Role.USER);
        }
        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            user.setPassword(null);
            return new AuthenticationResponse(user, jwtToken, false);
        }
        return new AuthenticationResponse(true);

    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
           try{
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               request.getEmail(),
                               request.getPassword()
                       )
               );
           }catch (BadCredentialsException e ){
               throw new UserExceptions(HttpStatus.NOT_FOUND, "User id or Password is wrong");
           }
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            String token = jwtService.generateToken(user);
            user.setPassword(null);
            return AuthenticationResponse.builder().user(user)
                    .token(token).isPresent(true)
                    .build();
        }else{
            throw new UserExceptions(HttpStatus.FORBIDDEN, "User id or Password is wrong");
        }
    }
}
