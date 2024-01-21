package com.example.hello.service

import com.example.hello.data.domain.Book
import com.example.hello.data.dto.BookReqDto
import com.example.hello.data.dto.BookResDto
import com.example.hello.data.repository.BookRepository
import com.example.hello.data.repository.BookshelfRepository
import jakarta.transaction.Transactional
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookService (
    private val bookRepository: BookRepository,
    private val bookshelfRepository: BookshelfRepository
) {
    // 책 생성하기
    fun createBook(reqDto: BookReqDto) {
        if (reqDto.bookshelf !== null) {
            val foundBookshelf = bookshelfRepository.findById(reqDto.bookshelf).orElseThrow{NotFoundException()}
            bookRepository.save(
                Book(
                    title = reqDto.title,
                    description = reqDto.description,
                    bookshelf = foundBookshelf
                )
            )
        } else {
            bookRepository.save(
                Book(
                    title = reqDto.title,
                    description = reqDto.description,
                    bookshelf = null
                )
            )
        }
    }
    // 책 가져오기
    fun findBook(id: Long): BookResDto? {
        val foundBook = bookRepository.findById(id).orElseThrow{NotFoundException()}
        return foundBook.bookshelf?.let {
            BookResDto(
                id = foundBook.id,
                title = foundBook.title,
                description = foundBook.description,
                bookshelfId = it.id
            )
        }
    }
    // 책 수정하기
    fun modifyBook(id: Long, reqDto: BookReqDto) {
        val foundBook = bookRepository.findById(id).orElseThrow{NotFoundException()}
        foundBook.updateBook(reqDto)
        if (reqDto.bookshelf != null) {
            val foundBookShelf = bookshelfRepository.findById(reqDto.bookshelf).orElseThrow{NotFoundException()}
            foundBook.updateBook(foundBookShelf)
        }
        bookRepository.save(foundBook)
    }
    // 책 삭제하기
    @Transactional
    fun deleteBook(id: Long) {
        bookRepository.deleteById(id)
    }
}