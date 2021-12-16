package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.model.BookModel
import com.gilvano.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

}
