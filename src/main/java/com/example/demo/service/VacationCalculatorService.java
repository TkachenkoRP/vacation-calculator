package com.example.demo.service;

import com.example.demo.model.SalaryInfo;
import org.springframework.stereotype.Service;

@Service
public class VacationCalculatorService {
    public double calculateVacationAmount(SalaryInfo salaryInfo) {
        return salaryInfo.getAverageSalary() / 30 * salaryInfo.getVacationDays();
    }
}
