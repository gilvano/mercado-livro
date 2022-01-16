package com.gilvano.mercadolivro.repository

import com.gilvano.mercadolivro.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setUp() = customerRepository.deleteAll()

    @Test
    fun `should return customer whose name containing`() {
        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))
        customerRepository.save(buildCustomer(name = "Alex"))

        val customers = customerRepository.findByNameContaining("Ma")

        assertEquals(listOf(marcos, matheus), customers)
    }

    @Nested
    inner class `exists by email` {
        @Test
        fun `should return true when email exists`() {
            val email = "email@teste.com"
            customerRepository.save(buildCustomer(email = email))

            val exists = customerRepository.existsByEmail(email)

            assertTrue(exists)
        }

        @Test
        fun `should return false when email do not exists`() {
            val email = "naoexisteemail@teste.com"

            val exists = customerRepository.existsByEmail(email)

            assertFalse(exists)
        }
    }

    @Nested
    inner class `find by email` {
        @Test
        fun `should return customer when email exists`() {
            val email = "email@teste.com"
            val customer = customerRepository.save(buildCustomer(email = email))
            val result = customerRepository.findByEmail(email)

            assertNotNull(result)
            assertEquals(customer, result)
        }

        @Test
        fun `should return customer when email do not exists`() {
            val email = "naoexisteemail@teste.com"

            val result = customerRepository.findByEmail(email)

            assertNull(result)
        }
    }
}