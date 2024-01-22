package com.example.hello.controller

import com.example.hello.data.dto.BookshelfReqDto
import com.example.hello.data.dto.BookshelfResDto
import com.example.hello.service.BookshelfService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookshelf")
class BookshelfController(
    val bookshelfService: BookshelfService
) {

    @PostMapping
    fun createBookshelf(
       @RequestBody reqDto: BookshelfReqDto
    ) {
        bookshelfService.createBookshelf(reqDto)
    }

    @GetMapping
    fun findBookshelf(
        @RequestParam id: Long
    ): BookshelfResDto {
        return bookshelfService.findBookShelf(id)
    }

    @PatchMapping
    fun modifyBookshelf(
        @RequestParam id: Long,
        @RequestBody reqDto: BookshelfReqDto
    ) {
        bookshelfService.modifyBookshelf(id, reqDto)
    }

    @DeleteMapping
    fun deleteBookshelf(
        @RequestParam id: Long
    ) {
        bookshelfService.deleteBookshelf(id)
    }
}