package com.book.store.dto;

import com.book.store.validation.PriceRangeValidation;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

@PriceRangeValidation
public record BookSearchParameters(
        String title,
        String[] authors,

        @Min(0)
        BigDecimal minPrice,
        @Min(0)
        BigDecimal maxPrice) {
}
