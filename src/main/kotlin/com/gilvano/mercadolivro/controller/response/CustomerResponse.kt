package com.gilvano.mercadolivro.controller.response

import com.gilvano.mercadolivro.enums.CustomerStatus

class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
