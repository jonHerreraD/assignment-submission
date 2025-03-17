package com.jonsnow.AssignmentSubmissionApp.service;

import com.jonsnow.AssignmentSubmissionApp.domain.Assignment;
import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    public Assignment create(User user){
        Assignment assignment = new Assignment();
        assignment.setStatus("Needs to be submitted");
        assignment.setUser(user);

        return assignmentRepository.save(assignment);
    }

    public Set<Assignment> findByUser(User user){
        return assignmentRepository.findByUser(user);
    }

    public Optional<Assignment> findById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId);
    }
}
