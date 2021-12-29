package com.gilvano.mercadolivro.controller.mapper

import com.gilvano.mercadolivro.controller.request.PostPurchaseRequest
import com.gilvano.mercadolivro.model.PurchaseModel
import com.gilvano.mercadolivro.service.BookService
import com.gilvano.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel{
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)
        return PurchaseModel(
            customer = customer,
            books = books,
            price = books.sumOf { it.price }
        )
    }
}