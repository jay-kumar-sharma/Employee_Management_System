package com.ems.attendanceservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSummaryDto {

    private Long employeeId;
    private int year;
    private int month;
    private int totalWorkingDays;
    private long presentDays;
    private long absentDays;
    private long leaveDays;
    private long earlyLeaveDays;
    private Double totalWorkingHours;
}
