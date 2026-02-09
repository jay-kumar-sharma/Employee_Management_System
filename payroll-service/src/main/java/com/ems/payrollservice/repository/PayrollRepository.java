package com.ems.payrollservice.repository;

import com.ems.payrollservice.entity.MonthlyPayroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<MonthlyPayroll, Long> {

    List<MonthlyPayroll> findByEmployeeId(Long employeeId);
}
