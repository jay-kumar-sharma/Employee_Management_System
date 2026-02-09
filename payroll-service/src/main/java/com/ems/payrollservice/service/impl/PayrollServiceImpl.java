package com.ems.payrollservice.service.impl;

import com.ems.payrollservice.dto.*;
import com.ems.payrollservice.entity.MonthlyPayroll;
import com.ems.payrollservice.feign.AttendanceClient;
import com.ems.payrollservice.feign.EmployeeClient;
import com.ems.payrollservice.repository.PayrollRepository;
import com.ems.payrollservice.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final EmployeeClient employeeClient;
    private final AttendanceClient attendanceClient;
    private final PayrollRepository repo;

    @Override
    public PayrollResponseDto generateMonthlyPayroll(Long employeeId, int year, int month) {

        // Fetch employee salary & profile
        EmployeeSalaryDto emp = employeeClient.getEmployee(employeeId);

        // Fetch attendance summary
        AttendanceSummaryDto summary = attendanceClient.getSummary(employeeId, year, month);

        // Calculate Daily Salary
        double dailySalary = emp.getBasicSalary() / summary.getTotalWorkingDays();

        // Deduction for absents
        double salaryDeduction = summary.getAbsentDays() * dailySalary;

        // Net Salary
        double netSalary = emp.getBasicSalary() - salaryDeduction;

        // Save payroll in DB
        MonthlyPayroll payroll = MonthlyPayroll.builder()
                .employeeId(employeeId)
                .year(year)
                .month(month)
                .basicSalary(emp.getBasicSalary())
                .totalWorkingDays(summary.getTotalWorkingDays())
                .presentDays((int) summary.getPresentDays())
                .absentDays((int) summary.getAbsentDays())
                .netSalary(netSalary)
                .build();

        repo.save(payroll);

        return new PayrollResponseDto(
                employeeId,
                emp.getFirstName() + " " + emp.getLastName(),
                year,
                month,
                emp.getBasicSalary(),
                summary.getTotalWorkingDays(),
                summary.getPresentDays(),
                summary.getAbsentDays(),
                netSalary
        );
    }
}
