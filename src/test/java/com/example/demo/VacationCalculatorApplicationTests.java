package com.example.demo;

import com.example.demo.dto.SalaryInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VacationCalculatorApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenSendSalaryInfo_thenGetVacationInfo() throws Exception {
        var response = 1000;
        var request = new SalaryInfoDto(30_000, 1);

        String actualResponse = mockMvc.perform(get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var actualVacationInfo = Double.parseDouble(actualResponse);

        assertEquals(response, actualVacationInfo);
    }

    @ParameterizedTest
    @MethodSource("invalidAverageSalary")
    void whenSendWrongAverageSalaryInSalaryInfo_thenReturnBadRequest(double averageSalary) throws Exception {
        var request = new SalaryInfoDto(averageSalary, 1);
        mockMvc.perform(get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("invalidVacationDays")
    void whenSendWrongVacationDaysInSalaryInfo_thenReturnBadRequest(int vacationDays) throws Exception {
        var request = new SalaryInfoDto(30_000, vacationDays);
        mockMvc.perform(get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSendSalaryInfoWithoutParameters_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSendSalaryInfoWithoutDto_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/calculate"))
                .andExpect(status().isInternalServerError());
    }

    private static Stream<Arguments> invalidAverageSalary() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(1_000_000_001)
        );
    }

    private static Stream<Arguments> invalidVacationDays() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(101)
        );
    }
}
