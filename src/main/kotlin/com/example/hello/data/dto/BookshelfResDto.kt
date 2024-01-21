package com.example.hello.data.dto

data class BookshelfResDto(
    val id: Long,
    val number: Long,
    val book: List<BookResDto>?
)
