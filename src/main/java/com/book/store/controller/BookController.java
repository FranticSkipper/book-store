package com.book.store.controller;

import com.book.store.dto.BookDto;
import com.book.store.dto.BookSearchParameters;
import com.book.store.dto.CreateBookRequestDto;
import com.book.store.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Management", description = "Endpoints for managing books.")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(
            summary = "Get all books.",
            description = "Retrieve a paginated list of all available "
                    + "books based on pagination and sorting parameters.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved a paginated list of books.")
    })
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get book by ID.",
            description = "Retrieve a book based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved a book by its ID."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found with the provided ID."
            )}
    )
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search books.",
            description = "Retrieve a page of all books that satisfy the search criteria.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved a page of "
                            + "books with the provided search criteria."
            )
    })
    public Page<BookDto> searchBooks(
            @ModelAttribute BookSearchParameters searchParameters,
            Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @PostMapping
    @Operation(
            summary = "Create new book.",
            description = "Create new book entity based on the input data.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New book entity was created successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided."
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update book.",
            description = "Update existing book with provided data.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book entity was updated successfully."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book with provided ID wasn't found."
            )
    })
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(
            @PathVariable Long id,
            @RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.updateById(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete book.",
            description = "Delete book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Book was deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book with provided ID wasn't found."
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
