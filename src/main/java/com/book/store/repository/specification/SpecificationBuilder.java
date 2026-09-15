package com.book.store.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<R, T> {
    Specification<R> build(T params);
}
