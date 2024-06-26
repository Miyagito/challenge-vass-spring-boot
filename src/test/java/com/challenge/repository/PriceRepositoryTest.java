package com.challenge.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.challenge.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceRepositoryTest {

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    public void setUp() {
        // Inicialización de los mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindApplicablePrices_Success() {
        // Dado
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        Pageable limit = PageRequest.of(0, 1, Sort.unsorted()); // Página con límite de 1
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(new Price());

        // Comportamiento simulado del repositorio
        when(priceRepository.findApplicablePrices(applicationDate, productId, brandId, limit))
                .thenReturn(expectedPrices);

        // Cuando
        // Llamada al método del repositorio con el nuevo parámetro Pageable
        List<Price> actualPrices = priceRepository.findApplicablePrices(applicationDate, productId, brandId, limit);

        // Entonces
        // Verificar que los precios retornados son los esperados
        assertEquals(expectedPrices, actualPrices);
    }

    @Test
    public void testFindApplicablePrices_EmptyResult() {
        // Dado
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;
        Pageable limit = PageRequest.of(0, 1, Sort.unsorted()); // Página con límite de 1

        // Comportamiento simulado del repositorio cuando no se encuentran precios
        when(priceRepository.findApplicablePrices(applicationDate, productId, brandId, limit))
                .thenReturn(new ArrayList<>());

        // Cuando
        // Llamada al método del repositorio
        List<Price> actualPrices = priceRepository.findApplicablePrices(applicationDate, productId, brandId, limit);

        // Entonces
        // Verificar que no se encontraron precios
        assertEquals(0, actualPrices.size());
    }
}
