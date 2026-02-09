package com.ems.payrollservice.service;

import com.ems.payrollservice.dto.PayrollResponseDto;

public interface PayrollService {

    PayrollResponseDto generateMonthlyPayroll(Long employeeId, int year, int month);
}
