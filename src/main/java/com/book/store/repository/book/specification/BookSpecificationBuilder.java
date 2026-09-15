package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import com.book.store.model.BookSearchParameters;
import com.book.store.repository.specification.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book, BookSearchParameters> {
    private final TitleSpecification titleSpecification;
    private final AuthorSpecification authorSpecification;
    private final MinPriceSpecification minPriceSpecification;
    private final MaxPriceSpecification maxPriceSpecification;

    @Override
    public Specification<Book> build(BookSearchParameters params) {
        Specification<Book> specification = Specification.unrestricted();

        if (params.title() != null && !params.title().isBlank()) {
            specification = specification
                    .and(titleSpecification.getSpecification(params.title()));
        }

        if (params.authors() != null && params.authors().length > 0) {
            specification = specification
                    .and(authorSpecification.getSpecification(params.authors()));
        }

        if (params.minPrice() != null) {
            specification = specification
                    .and(minPriceSpecification.getSpecification(params.minPrice()));
        }

        if (params.maxPrice() != null) {
            specification = specification
                    .and(maxPriceSpecification.getSpecification(params.maxPrice()));
        }

        return specification;
    }
}
