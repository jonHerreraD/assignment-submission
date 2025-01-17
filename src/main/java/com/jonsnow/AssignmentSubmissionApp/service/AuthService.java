package com.jonsnow.AssignmentSubmissionApp.service;


import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthCredentialsRequest;
import com.jonsnow.AssignmentSubmissionApp.dto.AuthResponse;
import com.jonsnow.AssignmentSubmissionApp.repository.UserRepository;
import com.jonsnow.AssignmentSubmissionApp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthResponse authenticate(AuthCredentialsRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token,user);
    }
}
