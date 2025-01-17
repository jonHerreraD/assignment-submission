package com.jonsnow.AssignmentSubmissionApp.service;

import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.repository.UserRepository;
import com.jonsnow.AssignmentSubmissionApp.util.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(()-> new UsernameNotFoundException("Invalid credentials"));
    }
}
