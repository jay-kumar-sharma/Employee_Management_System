package com.ems.attendanceservice.service;

import com.ems.attendanceservice.dto.AttendanceDto;
import com.ems.attendanceservice.dto.AttendanceSummaryDto;

public interface AttendanceService {

    String markAttendance(AttendanceDto dto);

    AttendanceSummaryDto getMonthlySummary(Long employeeId, int year, int month);
}
