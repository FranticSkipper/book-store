package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecification implements BookSpecificationProvider<String> {
    public Specification<Book> getSpecification(String title) {
        return (root, query, cb) -> {
            String lowerCaseEscapedTitle = title
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("%", "\\\\%")
                    .replaceAll("_", "\\\\_");

            return cb.like(cb.lower(root.get("title")), '%' + lowerCaseEscapedTitle + '%', '\\');
        };
    }
}
