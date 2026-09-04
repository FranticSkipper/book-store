package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecification implements BookSpecificationProvider<String[]> {
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> inPredicate = cb.in(root.get("author"));

            for (String str: params) {
                inPredicate.value(str);
            }

            return inPredicate;
        };
    }
}
