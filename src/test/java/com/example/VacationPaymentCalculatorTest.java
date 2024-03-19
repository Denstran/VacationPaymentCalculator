package com.example;

import com.example.request.VacationPaymentRequest;
import com.example.service.VacationPaymentCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationPaymentCalculatorTest {
    private VacationPaymentRequest request;
    private static VacationPaymentCalculator vacationPaymentCalculator;

    @BeforeAll
    static void beforeAll() {
        vacationPaymentCalculator = new VacationPaymentCalculator();
    }

    @Test
    void assert_that_counting_vacation_payment_correctly_without_start_date() {
        request = new VacationPaymentRequest(1000000, 14);

        double expectedResult = 39817.96;
        double actualResult = vacationPaymentCalculator.calculateVacationPayment(request);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void assert_that_counting_vacation_payment_correctly_with_start_date() {
        request = new VacationPaymentRequest
                (1000000, 14, LocalDate.of(2024, Month.MARCH, 1));

        double expectedResult = 25597.26;
        double actualResult = vacationPaymentCalculator.calculateVacationPayment(request);

        assertEquals(expectedResult, actualResult);
    }
}
