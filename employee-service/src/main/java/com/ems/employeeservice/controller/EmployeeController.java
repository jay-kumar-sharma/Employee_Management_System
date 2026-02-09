package com.ems.employeeservice.controller;

import com.ems.employeeservice.dto.EmployeeDto;
import com.ems.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto dto) {
        return service.createEmployee(dto);
    }

    @GetMapping("/{id}")
    public EmployeeDto get(@PathVariable Long id) {
        return service.getEmployee(id);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        return service.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Employee deleted!";
    }
}
