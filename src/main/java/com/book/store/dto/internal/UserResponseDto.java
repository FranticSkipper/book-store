package com.book.store.dto.internal;

public record UserResponseDto(Long id,
                              String email,
                              String firstName,
                              String lastName,
                              String shippingAddress) {
}
