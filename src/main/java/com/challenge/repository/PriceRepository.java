package com.challenge.repository;

import com.challenge.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Price> findApplicablePrices(LocalDateTime applicationDate, Integer productId, Integer brandId);

    default Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return findApplicablePrices(applicationDate, productId, brandId).stream().findFirst();
    }
}


