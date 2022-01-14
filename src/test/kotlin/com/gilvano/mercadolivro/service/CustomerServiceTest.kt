package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.enums.CustomerStatus
import com.gilvano.mercadolivro.enums.Role
import com.gilvano.mercadolivro.exception.NotFoundException
import com.gilvano.mercadolivro.model.CustomerModel
import com.gilvano.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.random.Random.Default.nextInt

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findAll() } returns fakeCustomers

        val customers = customerService.getAll(null)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContaining(any()) }
    }

    @Test
    fun `should return all customers when name is informed`() {
        val name = UUID.randomUUID().toString()
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContaining(name) } returns fakeCustomers

        val customers = customerService.getAll(name)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 1) { customerRepository.findByNameContaining(name) }
    }

    @Test
    fun `should create customer and encrypt password`() {
        val initialPassword = Random().nextInt().toString()
        val fakeCustomer = buildCustomer(password = initialPassword)
        val fakePassword = UUID.randomUUID().toString()
        val fakeCustomerEncrypted = fakeCustomer.copy(password = fakePassword)

        every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomer
        every { bCrypt.encode(initialPassword) } returns fakePassword

        customerService.create(fakeCustomer)

        verify(exactly = 1) { customerRepository.save(fakeCustomerEncrypted) }
        verify(exactly = 1) { bCrypt.encode(initialPassword) }
    }

    @Test
    fun `should return customer by id`() {
        val id = nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customer = customerService.findById(id = id)

        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw error when customer not found`() {
        val id = nextInt()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException>{
            customerService.findById(id)
        }

        assertEquals("Customer [${id}] not exists", error.message)
        assertEquals("ML-1101", error.errorCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    private fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "password",
    ) = CustomerModel(
            id = id,
            name = name,
            email = email,
            password = password,
            status = CustomerStatus.ATIVO,
            roles = setOf(Role.CUSTOMER)
        )


}