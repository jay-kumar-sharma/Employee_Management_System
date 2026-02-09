package com.ems.attendanceservice.controller;

import com.ems.attendanceservice.dto.AttendanceDto;
import com.ems.attendanceservice.dto.AttendanceSummaryDto;
import com.ems.attendanceservice.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping("/mark")
    public String markAttendance(@RequestBody AttendanceDto dto) {
        return service.markAttendance(dto);
    }

    @GetMapping("/summary/{employeeId}/{year}/{month}")
    public AttendanceSummaryDto getSummary(
            @PathVariable Long employeeId,
            @PathVariable int year,
            @PathVariable int month) {

        return service.getMonthlySummary(employeeId, year, month);
    }
}
