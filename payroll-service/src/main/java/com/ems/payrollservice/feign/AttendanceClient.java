package com.ems.payrollservice.feign;

import com.ems.payrollservice.dto.AttendanceSummaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "attendance-service")
public interface AttendanceClient {

    @GetMapping("/attendance/summary/{employeeId}/{year}/{month}")
    AttendanceSummaryDto getSummary(@PathVariable Long employeeId,
                                    @PathVariable int year,
                                    @PathVariable int month);
}
