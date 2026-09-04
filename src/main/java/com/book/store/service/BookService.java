package com.book.store.service;

import com.book.store.dto.BookDto;
import com.book.store.dto.CreateBookRequestDto;
import com.book.store.model.BookSearchParameters;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();

    BookDto getById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto book);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
