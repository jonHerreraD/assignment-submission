package com.jonsnow.AssignmentSubmissionApp.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentEnum {
    ASSIGNMENT_1(1, "HTML"),
    ASSIGNMENT_2(2, "CSS"),
    ASSIGNMENT_3(3, "Js"),
    ASSIGNMENT_4(4, "Java"),
    ASSIGNMENT_5(5, "POO"),
    ASSIGNMENT_6(6, "Data Structures"),
    ASSIGNMENT_7(7, "Trees"),
    ASSIGNMENT_8(8, "Algorithms"),
    ASSIGNMENT_9(9, "Binary Search"),
    ASSIGNMENT_10(10, "Spring"),
    ASSIGNMENT_11(11, "Spring Boot"),
    ASSIGNMENT_12(12, "Spring Security"),
    ASSIGNMENT_13(13, "Spring MVC"),
    ASSIGNMENT_14(14, "Microservices");

    private Integer asssignmentNum;
    private String assignmentName;

    AssignmentEnum (int asssignmentNum, String assignmentName){
        this.asssignmentNum = asssignmentNum;
        this.assignmentName = assignmentName;
    }



}
