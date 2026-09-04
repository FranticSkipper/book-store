package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MaxPriceSpecification implements BookSpecificationProvider<BigDecimal> {
    public Specification<Book> getSpecification(BigDecimal maxPrice) {
        return (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}
