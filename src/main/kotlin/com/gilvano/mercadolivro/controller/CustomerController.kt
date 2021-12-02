package com.gilvano.mercadolivro.controller

import com.gilvano.mercadolivro.controller.request.PostCustomerRequest
import com.gilvano.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {

    var customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(): List<CustomerModel> {
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest): CustomerModel {
        var id = (customers?.count()+1 ?: 1).toString()
        var newCustomer = CustomerModel(id, customer.name, customer.email)
        customers.add(newCustomer)
        return newCustomer
    }
}