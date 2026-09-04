package com.book.store.model;

import java.math.BigDecimal;

public record BookSearchParameters(
        String title,
        String[] authors,
        BigDecimal minPrice,
        BigDecimal maxPrice) {
    public BookSearchParameters {
        if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) < 0) {
            minPrice = null;
        }

        if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            maxPrice = null;
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0 ) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }
    }
}
