package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.controller.request.PostCustomerRequest
import com.gilvano.mercadolivro.controller.request.PutCustomerRequest
import com.gilvano.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Service
class CustomerService {
    var customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name) }
        }
        return customers
    }

    fun create(customer: PostCustomerRequest): CustomerModel {
        var id = (customers?.count()+1 ?: 1).toString()
        var newCustomer = CustomerModel(id, customer.name, customer.email)
        customers.add(newCustomer)
        return newCustomer
    }

    fun getCustomer(id: String): CustomerModel? {
        return customers.first { it.id == id }
    }

    fun update(id: String, customer: PutCustomerRequest) {
        customers.first { it.id == id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}