package com.gilvano.mercadolivro.controller

import com.gilvano.mercadolivro.controller.request.PostBookRequest
import com.gilvano.mercadolivro.controller.request.PutBookRequest
import com.gilvano.mercadolivro.controller.response.BookResponse
import com.gilvano.mercadolivro.extension.toBookModel
import com.gilvano.mercadolivro.extension.toResponse
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
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(): List<BookResponse> {
        return bookService.findAll().map { it.toResponse() }
    }

    @GetMapping("/actives")
    fun findActives(): List<BookResponse> {
        return bookService.findActives().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(request.toBookModel(bookSaved))
    }

}