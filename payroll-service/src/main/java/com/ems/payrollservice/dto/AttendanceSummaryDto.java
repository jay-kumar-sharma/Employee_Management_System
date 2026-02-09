package com.ems.payrollservice.dto;

import lombok.Data;

@Data
public class AttendanceSummaryDto {

    private Long employeeId;
    private int year;
    private int month;

    private int totalWorkingDays;
    private long presentDays;
    private long absentDays;

    private Double totalWorkingHours;
}
