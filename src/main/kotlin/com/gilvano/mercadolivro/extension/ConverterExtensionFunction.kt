package com.gilvano.mercadolivro.extension

import com.gilvano.mercadolivro.controller.request.PostBookRequest
import com.gilvano.mercadolivro.controller.request.PostCustomerRequest
import com.gilvano.mercadolivro.controller.request.PutBookRequest
import com.gilvano.mercadolivro.controller.request.PutCustomerRequest
import com.gilvano.mercadolivro.controller.response.BookResponse
import com.gilvano.mercadolivro.controller.response.CustomerResponse
import com.gilvano.mercadolivro.enums.BookStatus
import com.gilvano.mercadolivro.enums.CustomerStatus
import com.gilvano.mercadolivro.model.BookModel
import com.gilvano.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel?): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        status = this.status,
        customer = this.customer
    )
}