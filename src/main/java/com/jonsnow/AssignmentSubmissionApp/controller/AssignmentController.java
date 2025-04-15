package com.jonsnow.AssignmentSubmissionApp.controller;

import com.jonsnow.AssignmentSubmissionApp.domain.Assignment;
import com.jonsnow.AssignmentSubmissionApp.domain.User;
import com.jonsnow.AssignmentSubmissionApp.dto.AssignmentResponseDto;
import com.jonsnow.AssignmentSubmissionApp.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user){
        Assignment newAssignment = assignmentService.create(user);
        return ResponseEntity.ok(newAssignment);
    }

    @GetMapping("")
    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user){
        Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
        return ResponseEntity.ok(assignmentsByUser);
    }

    @GetMapping("{assignmentId}")
    public ResponseEntity<?> getAssignment(@PathVariable Long assignmentId, @AuthenticationPrincipal User user){
        Optional<Assignment> assignmentOpt = assignmentService.findById(assignmentId);
        return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment())));
    }

    @PutMapping("{assignmentId}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId,
                                              @RequestBody Assignment assignment,
                                              @AuthenticationPrincipal User user){
        Assignment updatedAssignment = assignmentService.update(assignment);
        return ResponseEntity.ok(updatedAssignment);
    }
}
