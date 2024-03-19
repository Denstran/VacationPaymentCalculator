package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacationPaymentRequest {
    @Positive(message = "Зарплата должна быть больше нуля!")
    private double averageSalaryPerYear;
    @Positive(message = "Количество дней отпуска должно быть больше нуля!")
    private int vacationDaysAmount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Дата начала отпуска должна быть в будущем!")
    private LocalDate vacationStartDate;

    public VacationPaymentRequest(double averageSalaryPerYear, int vacationDaysAmount) {
        this.averageSalaryPerYear = averageSalaryPerYear;
        this.vacationDaysAmount = vacationDaysAmount;
    }

    public Optional<LocalDate> getVacationStartDate() {
        return Optional.ofNullable(vacationStartDate);
    }

    public double getAveragePaymentPerDay() {
        double averageAmountOfDaysInMonth = 29.3;
        double amountOfDaysPerMonthNotFullyWorked = 0;
        int amountOfMonths = 12;
        double amountOfFullyWorkedDays =
                averageAmountOfDaysInMonth * amountOfMonths + amountOfDaysPerMonthNotFullyWorked;

        return Math.round((averageSalaryPerYear / amountOfFullyWorkedDays) * 100.0) / 100.0;
    }
}
