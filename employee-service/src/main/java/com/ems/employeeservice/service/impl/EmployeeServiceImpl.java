package com.ems.employeeservice.service.impl;

import com.ems.employeeservice.dto.EmployeeDto;
import com.ems.employeeservice.entity.Employee;
import com.ems.employeeservice.exception.EmployeeNotFoundException;
import com.ems.employeeservice.repository.EmployeeRepository;
import com.ems.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {

        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);

        // Generate new employee code
        long count = repo.count() + 1;
        String code = String.format("EMP-%05d", count);
        employee.setEmployeeCode(code);

        Employee saved = repo.save(employee);

        EmployeeDto response = new EmployeeDto();
        BeanUtils.copyProperties(saved, response);
        return response;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));

        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return repo.findAll().stream().map(emp -> {
            EmployeeDto dto = new EmployeeDto();
            BeanUtils.copyProperties(emp, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {

        Employee employee = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));

        BeanUtils.copyProperties(dto, employee, "id", "employeeCode");

        Employee updated = repo.save(employee);

        EmployeeDto response = new EmployeeDto();
        BeanUtils.copyProperties(updated, response);
        return response;
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee Not Found!");
        }
        repo.deleteById(id);
    }
}
