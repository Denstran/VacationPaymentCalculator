package com.example.service;

import com.example.request.VacationPaymentRequest;
import com.example.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationPaymentCalculator {

    public double calculateVacationPayment(VacationPaymentRequest vacationPaymentRequest) {
        if (vacationPaymentRequest.getVacationStartDate().isPresent())
            return calculateVacationPaymentWithSelectedVacationDate(vacationPaymentRequest);
        else
            return calculateVacationPaymentWithoutSelectedVacationDate(vacationPaymentRequest);
    }

    private double calculateVacationPaymentWithoutSelectedVacationDate(VacationPaymentRequest vacationPaymentRequest) {
        double vacationPayment
                = vacationPaymentRequest.getAveragePaymentPerDay() * vacationPaymentRequest.getVacationDaysAmount();
        return Math.round(vacationPayment * 100.0) / 100.0;
    }

    private double calculateVacationPaymentWithSelectedVacationDate(VacationPaymentRequest vacationPaymentRequest) {
        LocalDate vacationStartDate = vacationPaymentRequest.getVacationStartDate().get();
        long vacationDaysAmount =
                countVacationDaysLeft(vacationStartDate, vacationPaymentRequest.getVacationDaysAmount());

        double vacationPayment = vacationPaymentRequest.getAveragePaymentPerDay() * vacationDaysAmount;
        return Math.round(vacationPayment * 100.0) / 100.0;
    }

    private long countVacationDaysLeft(LocalDate vacationStartDate, long vacationDaysAmount) {
        LocalDate vacationEndDate = vacationStartDate.plusDays(vacationDaysAmount);
        long amountOfWeekendsInVacation = DateUtils.countWeekEndsInDateRange(vacationStartDate, vacationEndDate);
        long amountOfHolidaysInVacation = DateUtils.countHolidaysInDateRange(vacationStartDate, vacationEndDate);

        return vacationDaysAmount - (amountOfHolidaysInVacation + amountOfWeekendsInVacation);
    }
}
