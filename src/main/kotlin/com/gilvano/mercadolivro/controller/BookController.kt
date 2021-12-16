package com.gilvano.mercadolivro.controller

import com.gilvano.mercadolivro.controller.request.PostBookRequest
import com.gilvano.mercadolivro.extension.toBookModel
import com.gilvano.mercadolivro.model.BookModel
import com.gilvano.mercadolivro.service.BookService
import com.gilvano.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

}