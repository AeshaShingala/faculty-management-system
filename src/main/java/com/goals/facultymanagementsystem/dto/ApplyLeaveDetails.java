package com.goals.facultymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyLeaveDetails {

    private String name;
    private Collection<LeaveDetails> leaves;
}
