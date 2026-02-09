package com.ems.payrollservice.dto;

import lombok.Data;

@Data
public class EmployeeSalaryDto {
    private Long id;
    private Double basicSalary;
    private String firstName;
    private String lastName;
}
