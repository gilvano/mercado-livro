package com.gilvano.mercadolivro.helper

import com.gilvano.mercadolivro.enums.CustomerStatus
import com.gilvano.mercadolivro.enums.Role
import com.gilvano.mercadolivro.model.BookModel
import com.gilvano.mercadolivro.model.CustomerModel
import com.gilvano.mercadolivro.model.PurchaseModel
import java.math.BigDecimal
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String = "customer name",
    email: String = "${UUID.randomUUID()}@email.com",
    password: String = "password",
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    password = password,
    status = CustomerStatus.ATIVO,
    roles = setOf(Role.CUSTOMER)
)

fun buildPurchase(
    id: Int? = null,
    customer: CustomerModel = buildCustomer(),
    books: MutableList<BookModel> = mutableListOf<BookModel>(),
    nfe: String? = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN

) = PurchaseModel(
    id = id,
    customer = customer,
    books = books,
    nfe = nfe,
    price = price
)