package com.ems.payrollservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollResponseDto {

    private Long employeeId;
    private String employeeName;

    private int year;
    private int month;

    private Double basicSalary;

    private int totalWorkingDays;
    private long presentDays;
    private long absentDays;

    private Double netSalary;
}
