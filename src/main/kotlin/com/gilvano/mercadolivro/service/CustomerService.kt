package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.enums.CustomerStatus
import com.gilvano.mercadolivro.enums.Errors
import com.gilvano.mercadolivro.enums.Profile
import com.gilvano.mercadolivro.exception.NotFoundException
import com.gilvano.mercadolivro.model.CustomerModel
import com.gilvano.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder
) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel): CustomerModel {
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER),
            password = bCrypt.encode(customer.password),
        )
        return customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow { NotFoundException(Errors.ML1101.message.format(id), Errors.ML1101.code) }
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}