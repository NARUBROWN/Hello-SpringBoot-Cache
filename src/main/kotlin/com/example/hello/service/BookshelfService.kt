package com.example.hello.service

import com.example.hello.data.domain.Book
import com.example.hello.data.domain.Bookshelf
import com.example.hello.data.dto.BookResDto
import com.example.hello.data.dto.BookshelfReqDto
import com.example.hello.data.dto.BookshelfResDto
import com.example.hello.data.repository.BookshelfRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookshelfService(
    private val bookshelfRepository: BookshelfRepository
) {
    // 책장 만들기
    fun createBookshelf(reqDto: BookshelfReqDto) {
        bookshelfRepository.save(
            Bookshelf(
                number = reqDto.number
            )
        )
    }
    // 책장 가져오기
    fun findBookShelf(id: Long): BookshelfResDto {
        val foundBookShelf = bookshelfRepository.findById(id).orElseThrow{NotFoundException()}
        return if (foundBookShelf.book != null) {
            BookshelfResDto(
                id = foundBookShelf.id,
                number = foundBookShelf.number,
                book = createList(foundBookShelf.book!!)
            )
        } else {
            BookshelfResDto(
                id = foundBookShelf.id,
                number = foundBookShelf.number,
                book = null
            )
        }
    }
    // 책장 수정하기
    fun modifyBookshelf(id: Long, reqDto: BookshelfReqDto) {
        val foundBookShelf = bookshelfRepository.findById(id).orElseThrow{NotFoundException()}
        foundBookShelf.updateBookshelf(reqDto)
        bookshelfRepository.save(foundBookShelf)
    }
    // 책장 삭제하기
    fun deleteBookshelf(id: Long) {
        bookshelfRepository.deleteById(id)
    }

    fun createList(bookList: List<Book>): List<BookResDto> {
        val bookResDtoList: MutableList<BookResDto> = mutableListOf()
        for (i in bookList.indices) {
            bookResDtoList.add(
                BookResDto(
                    id = bookList[i].id,
                    description = bookList[i].description,
                    title = bookList[i].title,
                    bookshelfId = bookList[i].bookshelf?.id
                )
            )
        }
        return bookResDtoList
    }
}