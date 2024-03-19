package com.challenge.controller;

import com.challenge.domain.Price;
import com.challenge.dto.PriceResponseDto;
import com.challenge.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getApplicablePrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Integer productId,
            @RequestParam Integer brandId) {

        Optional<Price> price = priceService.findApplicablePrice(applicationDate, productId, brandId);
        return price.map(p -> new PriceResponseDto(
                        p.getProductId(),
                        p.getBrandId(),
                        p.getPriceList(),
                        p.getStartDate(),
                        p.getEndDate(),
                        p.getPrice()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


