package com.challenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Prueba para obtener el precio aplicable para una fecha, un producto y una marca válidos
    @Test
    public void testGetApplicablePrice() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").exists());
    }

    // Prueba para verificar que se maneje correctamente un ID de producto inválido
    @Test
    public void testGetApplicablePriceWithInvalidProductId() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "invalid-id")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }

    // Prueba para verificar que se maneje correctamente una fecha de aplicación inválida
    @Test
    public void testGetApplicablePriceWithInvalidDate() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "not-a-date")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest());
    }

    // Prueba para verificar que se maneje correctamente un ID de marca inválido
    @Test
    public void testGetApplicablePriceWithInvalidBrandId() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "invalid-id"))
                .andExpect(status().isBadRequest());
    }
}
