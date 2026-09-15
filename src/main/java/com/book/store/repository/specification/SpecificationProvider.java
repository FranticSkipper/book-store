package com.book.store.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<R, T> {
    Specification<R> getSpecification(T params);
}
