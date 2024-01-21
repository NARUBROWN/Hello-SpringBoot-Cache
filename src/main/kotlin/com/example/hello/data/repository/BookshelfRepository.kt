package com.example.hello.data.repository

import com.example.hello.data.domain.Bookshelf
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookshelfRepository: JpaRepository<Bookshelf, Long> {
}