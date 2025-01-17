package com.jonsnow.AssignmentSubmissionApp.dto;

import com.jonsnow.AssignmentSubmissionApp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private User user;
}
