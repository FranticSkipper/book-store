package com.book.store.dto;

import com.book.store.validation.PriceRangeValidation;
import java.math.BigDecimal;

@PriceRangeValidation
public record BookSearchParameters(
        String title,
        String[] authors,
        BigDecimal minPrice,
        BigDecimal maxPrice) {
}
