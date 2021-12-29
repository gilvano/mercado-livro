package com.gilvano.mercadolivro.events.listener

import com.gilvano.mercadolivro.events.PurchaseEvent
import com.gilvano.mercadolivro.service.BookService
import com.gilvano.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}