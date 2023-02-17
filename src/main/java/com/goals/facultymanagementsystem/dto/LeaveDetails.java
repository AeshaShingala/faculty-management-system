package com.goals.facultymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaveDetails {
    private String reason;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate date;
}
