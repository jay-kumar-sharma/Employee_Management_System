package com.ems.payrollservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_payroll")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyPayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private int year;
    private int month;

    private Double basicSalary;

    private int totalWorkingDays;
    private int presentDays;
    private int absentDays;

    private Double netSalary;

    @CreationTimestamp
    private LocalDateTime generatedAt;
}
