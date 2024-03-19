package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class VacationPaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculateVacationPaymentTest() throws Exception {
        double expectedVacationPayment = 28441.4;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalaryPerYear", "1000000")
                        .param("vacationDaysAmount", "14")
                        .param("vacationStartDate", "2024-04-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPayment").value(expectedVacationPayment));
    }

    @Test
    public void calculateVacationPaymentWithoutStartDateTest() throws Exception {
        double expectedVacationPayment = 39817.96;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalaryPerYear", "1000000")
                        .param("vacationDaysAmount", "14")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPayment").value(expectedVacationPayment));
    }
}
