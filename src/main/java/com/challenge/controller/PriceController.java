package com.challenge.controller;

import com.challenge.domain.Price;
import com.challenge.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Price> getApplicablePrice(@RequestParam LocalDateTime applicationDate,
                                                    @RequestParam Integer productId,
                                                    @RequestParam Integer brandId) {
        Optional<Price> price = priceService.findApplicablePrice(applicationDate, productId, brandId);
        return price.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

