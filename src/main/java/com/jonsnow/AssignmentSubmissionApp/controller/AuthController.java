package com.jonsnow.AssignmentSubmissionApp.controller;

import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthCredentialsRequest;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthResponse;
import com.jonsnow.AssignmentSubmissionApp.service.AuthService;
import com.jonsnow.AssignmentSubmissionApp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController{
    
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request){
        try{
            AuthResponse authResponse = authService.authenticate(request);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, authResponse.getToken())
                    .body(authResponse.getUser());
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
