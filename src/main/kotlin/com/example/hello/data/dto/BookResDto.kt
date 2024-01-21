package com.example.hello.data.dto

data class BookResDto(
    val id: Long,
    val title: String,
    val description: String,
    val bookshelfId: Long?
)
