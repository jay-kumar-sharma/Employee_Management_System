package com.ems.payrollservice.controller;

import com.ems.payrollservice.dto.PayrollResponseDto;
import com.ems.payrollservice.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService service;

    @PostMapping("/generate/{employeeId}/{year}/{month}")
    public PayrollResponseDto generate(
            @PathVariable Long employeeId,
            @PathVariable int year,
            @PathVariable int month) {

        return service.generateMonthlyPayroll(employeeId, year, month);
    }
}
