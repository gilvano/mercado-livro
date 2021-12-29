package com.gilvano.mercadolivro.service

import com.gilvano.mercadolivro.model.PurchaseModel
import com.gilvano.mercadolivro.repository.PurchaseRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository
) {

    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)
    }
}
