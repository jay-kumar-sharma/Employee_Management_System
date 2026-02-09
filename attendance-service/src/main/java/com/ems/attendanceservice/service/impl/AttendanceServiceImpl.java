package com.ems.attendanceservice.service.impl;

import com.ems.attendanceservice.dto.*;
import com.ems.attendanceservice.entity.AttendanceRecord;
import com.ems.attendanceservice.feign.EmployeeClient;
import com.ems.attendanceservice.repository.AttendanceRepository;
import com.ems.attendanceservice.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repo;
    private final EmployeeClient employeeClient;

    @Override
    public String markAttendance(AttendanceDto dto) {

        // Validate employee exists
        employeeClient.getEmployee(dto.getEmployeeId()); // Throws error if not exists

        AttendanceRecord record = new AttendanceRecord();
        BeanUtils.copyProperties(dto, record);

        // Late check-in check (9:00 AM)
        if (dto.getCheckInTime() != null) {
            LocalTime officeStart = LocalTime.of(9, 0);
            record.setLateCheckIn(dto.getCheckInTime().toLocalTime().isAfter(officeStart));
        }

        // Early check-out (before 5:00 PM)
        if (dto.getCheckOutTime() != null) {
            LocalTime officeEnd = LocalTime.of(17, 0);
            record.setEarlyCheckOut(dto.getCheckOutTime().toLocalTime().isBefore(officeEnd));
        }

        // Calculate working hours
        if (dto.getCheckInTime() != null && dto.getCheckOutTime() != null) {
            Duration duration = Duration.between(dto.getCheckInTime(), dto.getCheckOutTime());
            record.setWorkingHours(duration.toMinutes() / 60.0);
        }

        repo.save(record);
        return "Attendance marked successfully!";
    }

    @Override
    public AttendanceSummaryDto getMonthlySummary(Long employeeId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<AttendanceRecord> records =
                repo.findByEmployeeIdAndAttendanceDateBetween(employeeId, start, end);

        long present = records.stream().filter(r -> "PRESENT".equals(r.getStatus())).count();
        long absent = records.stream().filter(r -> "ABSENT".equals(r.getStatus())).count();
        long leave = records.stream().filter(r -> "LEAVE".equals(r.getStatus())).count();
        long earlyLeave = records.stream().filter(r -> Boolean.TRUE.equals(r.getEarlyCheckOut())).count();
        double totalHours = records.stream().mapToDouble(r -> r.getWorkingHours() != null ? r.getWorkingHours() : 0).sum();

        return new AttendanceSummaryDto(
                employeeId, year, month, records.size(), present, absent, leave, earlyLeave, totalHours
        );
    }
}
