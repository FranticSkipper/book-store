package com.book.store.service.impl;

import com.book.store.dto.BookDto;
import com.book.store.dto.CreateBookRequestDto;
import com.book.store.exception.EntityNotFoundException;
import com.book.store.mapper.BookMapper;
import com.book.store.model.Book;
import com.book.store.model.BookSearchParameters;
import com.book.store.repository.book.BookRepository;
import com.book.store.repository.specification.SpecificationBuilder;
import com.book.store.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SpecificationBuilder<Book, BookSearchParameters> bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with id = %d is not found.", id)));

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with id = %d is not found.", id)));

        bookMapper.updateBookFromDto(bookDto, book);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    String.format("Book with id = %d does not exist.", id));
        }

        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameters params) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);

        return bookRepository
                .findAll(bookSpecification)
                .stream().map(bookMapper::toDto)
                .toList();
    }
}
