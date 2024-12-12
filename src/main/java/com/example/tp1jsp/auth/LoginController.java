package com.example.tp1jsp.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;

    public LoginController(AuthenticationManager authenticationManager, TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username,
                            loginRequest.password
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenGenerator.generateJwtToken(authentication);

            return ResponseEntity.ok("Bearer " + jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Mauvais utilisateur ou mot de passe");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public record LoginRequest(String username, String password, String role) {
    }

}