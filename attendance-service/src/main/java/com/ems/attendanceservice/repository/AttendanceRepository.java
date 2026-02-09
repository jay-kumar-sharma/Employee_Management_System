package com.ems.attendanceservice.repository;

import com.ems.attendanceservice.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findByEmployeeIdAndAttendanceDateBetween(
            Long employeeId, LocalDate start, LocalDate end);

}
