package com.example.task.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Project {

    private String projectName;
    private LocalDateTime springStartDate;
    private LocalDateTime springEndDate;
}
