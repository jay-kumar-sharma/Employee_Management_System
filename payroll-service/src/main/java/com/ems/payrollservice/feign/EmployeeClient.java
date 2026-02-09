package com.ems.payrollservice.feign;

import com.ems.payrollservice.dto.EmployeeSalaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/employees/{id}")
    EmployeeSalaryDto getEmployee(@PathVariable Long id);
}
