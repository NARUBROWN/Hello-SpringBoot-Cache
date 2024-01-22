package com.example.hello.data.domain

import com.example.hello.data.dto.BookshelfReqDto
import jakarta.persistence.*

@Entity
class Bookshelf(
    number: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var number: Long = number
        protected set

    // 복습) mappedBy는 연관관계의 주인 엔티티의 필드를 적는것이다.Book.bookshelf
    @OneToMany(mappedBy = "bookshelf", cascade = [CascadeType.REMOVE])
    var book: List<Book>? = null
        protected set

    fun updateBookshelf(reqDto: BookshelfReqDto) {
        this.number = reqDto.number
    }
}