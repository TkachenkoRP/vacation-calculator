package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfoDto {
    @NotNull(message = "Средняя зарплата должна быть указана!")
    @Positive(message = "Средняя зарплата должна быть больше 0!")
    @Max(value = 1_000_000_000, message = "Средняя зарплата не может быть более {value}!")
    private double averageSalary;

    @NotNull(message = "Количество дней отпуска должно быть указано!")
    @Positive(message = "Количество дней отпуска должно быть больше 0!")
    @Max(value = 100, message = "Количество дней отпуска не может быть более {value}!")
    private int vacationDays;
}
