package com.ems.employeeservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDto {

    private Long id;
    private String employeeCode;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;

    private LocalDate dateOfJoining;
    private String department;
    private String designation;
    private String employmentType;
    private String status;

    private BigDecimal basicSalary;
}
