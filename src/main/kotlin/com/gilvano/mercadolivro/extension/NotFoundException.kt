package com.gilvano.mercadolivro.extension

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}