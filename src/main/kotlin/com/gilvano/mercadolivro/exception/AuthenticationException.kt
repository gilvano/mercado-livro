package com.gilvano.mercadolivro.exception

class AuthenticationException(override val message: String, val errorCode: String): Exception() {
}