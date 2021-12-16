package com.gilvano.mercadolivro.repository

import com.gilvano.mercadolivro.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {

}