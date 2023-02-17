package com.goals.facultymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDetails {
    private long id;
    private String name;
    private String email;
    private String subject;
    private QualificationDetails qualificationDetails;
}
