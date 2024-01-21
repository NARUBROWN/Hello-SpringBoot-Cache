package com.example.hello.controller

import com.example.hello.data.dto.BookReqDto
import com.example.hello.data.dto.BookResDto
import com.example.hello.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(
    private val bookService: BookService
) {
    // 책 만들기
    @PostMapping
    fun createBook(
        @RequestBody reqDto: BookReqDto
    ) {
        bookService.createBook(reqDto)
    }

    // 책 가져오기
    @GetMapping
    fun findBook(
        @RequestParam("id") id: Long
    ): BookResDto? {
        return bookService.findBook(id)
    }

    // 책 수정하기
    @PatchMapping
    fun modifyBook(
        @RequestBody reqDto: BookReqDto,
        @RequestParam("id") id: Long
    ) {
        bookService.modifyBook(id, reqDto)
    }

    // 책 지우기
    @DeleteMapping
    fun deleteBook(
        @RequestParam("id") id: Long
    ) {
        bookService.deleteBook(id)
    }

}