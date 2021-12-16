package com.gilvano.mercadolivro.controller.response

import com.gilvano.mercadolivro.enums.BookStatus
import com.gilvano.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus? = null
)
