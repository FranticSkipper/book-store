package com.book.store.service;

import com.book.store.dto.BookDto;
import com.book.store.dto.BookSearchParameters;
import com.book.store.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    Page<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto book);

    void deleteById(Long id);

    Page<BookDto> search(BookSearchParameters params, Pageable pageable);
}
