package com.gilvano.mercadolivro.exception

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}