package com.challenge.service;

import com.challenge.domain.Price;
import com.challenge.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);
        // Instanciación del servicio a probar con los mocks inyectados
        priceService = new PriceService(priceRepository);
    }

    @Test
    public void testFindApplicablePrice_Success() {
        // Dado
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        // Creación de un precio esperado
        Price expectedPrice = new Price();

        // Comportamiento simulado del repositorio
        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));

        // Cuando
        // Llamada al método del servicio
        Optional<Price> actualPrice = priceService.findApplicablePrice(applicationDate, productId, brandId);

        // Entonces
        // Verificación de que el precio retornado es el esperado
        assertEquals(Optional.of(expectedPrice), actualPrice);
    }

    @Test
    public void testFindApplicablePrice_NoPriceFound() {
        // Dado
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // Comportamiento simulado del repositorio para cuando no se encuentra un precio
        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());

        // Cuando
        // Llamada al método del servicio
        Optional<Price> actualPrice = priceService.findApplicablePrice(applicationDate, productId, brandId);

        // Entonces
        // Verificación de que no se encontró ningún precio
        assertEquals(Optional.empty(), actualPrice);
    }
}
