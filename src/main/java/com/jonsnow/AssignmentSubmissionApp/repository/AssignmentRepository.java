package com.jonsnow.AssignmentSubmissionApp.repository;

import com.jonsnow.AssignmentSubmissionApp.domain.Assignment;
import com.jonsnow.AssignmentSubmissionApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    Set<Assignment> findByUser(User user);
}
