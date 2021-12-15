package com.gilvano.mercadolivro.repository

import com.gilvano.mercadolivro.enums.BookStatus
import com.gilvano.mercadolivro.model.BookModel
import com.gilvano.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerModel) : List<BookModel>

}