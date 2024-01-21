package com.example.hello.service

import com.example.hello.data.domain.Book
import com.example.hello.data.domain.Bookshelf
import com.example.hello.data.dto.BookResDto
import com.example.hello.data.dto.BookshelfReqDto
import com.example.hello.data.dto.BookshelfResDto
import com.example.hello.data.repository.BookRepository
import com.example.hello.data.repository.BookshelfRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BookshelfService(
    private val bookRepository: BookRepository,
    private val bookshelfRepository: BookshelfRepository
) {
    // 책장 만들기
    fun createBookshelf(reqDto: BookshelfReqDto) {
        if (reqDto.bookId == null){
            bookshelfRepository.save(
                Bookshelf(
                    number = reqDto.number,
                    book = null
                )
            )
        } else {
            val foundBookList = bookRepository.findAllById(reqDto.bookId)
            bookshelfRepository.save(
                bookshelfRepository.save(
                    Bookshelf(
                        number = reqDto.number,
                        book = foundBookList
                    )
                )
            )
        }
    }
    // 책장 가져오기
    fun findBookShelf(id: Long): BookshelfResDto {
        val foundBookShelf = bookshelfRepository.findById(id).orElseThrow{NotFoundException()}
        if (foundBookShelf.book != null) {
            return BookshelfResDto(
                id = foundBookShelf.id,
                number = foundBookShelf.number,
                book = createList(foundBookShelf.book!!)
            )
        } else {
            return BookshelfResDto(
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
    }
    // 책장 삭제하기
    fun deleteBookshelf(id: Long) {
        bookshelfRepository.deleteById(id)
    }

    fun createList(bookList: List<Book>): List<BookResDto> {
        val bookResDtoList = emptyList<BookResDto>()
        for (i in 0..bookList.size) {
            bookResDtoList.addLast(
                BookResDto(
                    id = bookList.get(i).id,
                    description = bookList.get(i).description,
                    title = bookList.get(i).title,
                    bookshelfId = null
                )
            )
        }
        return bookResDtoList
    }
}