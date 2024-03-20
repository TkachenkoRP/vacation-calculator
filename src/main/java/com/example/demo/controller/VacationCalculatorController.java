package com.example.demo.controller;

import com.example.demo.dto.SalaryInfoDto;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.service.VacationCalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;
    private final SalaryInfoMapper salaryInfoMapper;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateVacationCompensation(@RequestBody @Valid SalaryInfoDto salaryInfo) {
        return ResponseEntity.ok(vacationCalculatorService.calculateVacationAmount(salaryInfoMapper.toEntity(salaryInfo)));
    }
}
