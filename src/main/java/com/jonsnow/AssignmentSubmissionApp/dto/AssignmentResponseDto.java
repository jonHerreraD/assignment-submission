package com.jonsnow.AssignmentSubmissionApp.dto;

import com.jonsnow.AssignmentSubmissionApp.domain.Assignment;
import com.jonsnow.AssignmentSubmissionApp.enums.AssignmentEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponseDto {
    private Assignment assignment;
    private AssignmentEnum[] assignmentEnums = AssignmentEnum.values();

    public AssignmentResponseDto(Assignment assignment){
        super();
        this.assignment = assignment;
    }

}
