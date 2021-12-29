package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.events.PurchaseEvent
import com.gilvano.mercadolivro.model.PurchaseModel
import com.gilvano.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository,
        private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }
}
