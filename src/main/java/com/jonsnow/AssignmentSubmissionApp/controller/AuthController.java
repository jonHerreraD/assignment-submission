package com.jonsnow.AssignmentSubmissionApp.controller;

import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthCredentialsRequest;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthResponse;
import com.jonsnow.AssignmentSubmissionApp.service.AuthService;
import com.jonsnow.AssignmentSubmissionApp.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController{
    
    private final AuthService authService;
    private final JwtUtil jwtUtil;

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

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user){

        try{
            Boolean isTokenValid = jwtUtil.validateToken(token,user);
            return ResponseEntity.ok(isTokenValid);
        }catch (ExpiredJwtException e){
            return ResponseEntity.ok(false);
        }


    }

}
