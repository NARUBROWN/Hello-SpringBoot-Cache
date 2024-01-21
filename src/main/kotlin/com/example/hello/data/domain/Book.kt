package com.example.hello.data.domain

import com.example.hello.data.dto.BookReqDto
import jakarta.persistence.*

@Entity
class Book(
    title: String,
    description: String,
    bookshelf: Bookshelf?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @Column
    var title: String = title
        protected set

    @Column
    var description: String = description
        protected set

    @ManyToOne
    // JoinColumn은 외래키 이름을 넣어야 한다. 필드명 + 참조하는 테이블의 PK 컬럼명
    @JoinColumn(name = "bookshelf_id")
    var bookshelf: Bookshelf? = bookshelf
        protected set


    fun updateBook(reqDto: BookReqDto) {
        this.title = reqDto.title
        this.description = reqDto.description
    }

    fun updateBook(bookshelf: Bookshelf) {
        this.bookshelf = bookshelf
    }

}