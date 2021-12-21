package com.gilvano.mercadolivro.controller.request

import javax.validation.constraints.NotEmpty

data class PutCustomerRequest(

    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:NotEmpty(message = "E-mail deve ser válido")
    var email: String
)