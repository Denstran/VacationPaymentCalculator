package com.example.controller;

import com.example.request.VacationPaymentRequest;
import com.example.response.VacationPaymentResponse;
import com.example.service.VacationPaymentCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VacationPaymentController {
    private final VacationPaymentCalculator vacationPaymentCalculator;

    @Autowired
    public VacationPaymentController(VacationPaymentCalculator vacationPaymentCalculator) {
        this.vacationPaymentCalculator = vacationPaymentCalculator;
    }

    @GetMapping("/calculate")
    public ResponseEntity<VacationPaymentResponse> calculateVacationPayment(@Valid VacationPaymentRequest vacationPaymentRequest) {

        double vacationPayment = vacationPaymentCalculator.calculateVacationPayment(vacationPaymentRequest);
        return ResponseEntity.ok(new VacationPaymentResponse(vacationPayment));
    }
}
