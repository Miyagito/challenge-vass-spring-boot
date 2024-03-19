package com.challenge.service;

import com.challenge.domain.Price;
import com.challenge.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId);
    }
}

