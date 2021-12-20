package com.gilvano.mercadolivro.exception

import com.gilvano.mercadolivro.controller.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(400, "Este recursos não existe", "0001", null)

        return ResponseEntity.badRequest().body(erro)
    }
}