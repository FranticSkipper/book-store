package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MinPriceSpecification implements BookSpecificationProvider<BigDecimal> {
    public Specification<Book> getSpecification(BigDecimal minPrice) {
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }
}
