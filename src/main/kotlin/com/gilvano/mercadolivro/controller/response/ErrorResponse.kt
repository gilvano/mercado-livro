package com.gilvano.mercadolivro.controller.response

import com.gilvano.mercadolivro.controller.response.FieldErrorResponse

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?
)
