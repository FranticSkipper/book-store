package com.book.store.validation;

import com.book.store.dto.BookSearchParameters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NonNull;

public class PriceRangeValidator
        implements ConstraintValidator<PriceRangeValidation, BookSearchParameters> {
    @Override
    public boolean isValid(
            @NonNull BookSearchParameters bookSearchParameters,
            ConstraintValidatorContext context) {
        if (bookSearchParameters.minPrice() == null
                || bookSearchParameters.maxPrice() == null) {
            return true;
        }

        return bookSearchParameters.maxPrice().compareTo(bookSearchParameters.minPrice()) >= 0;
    }
}
