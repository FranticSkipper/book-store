package com.book.store.repository.book.specification;

import com.book.store.model.Book;
import com.book.store.repository.specification.SpecificationProvider;

public interface BookSpecificationProvider<T> extends SpecificationProvider<Book, T> {
}
