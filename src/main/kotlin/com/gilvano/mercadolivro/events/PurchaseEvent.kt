package com.gilvano.mercadolivro.events

import com.gilvano.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchaseModel: PurchaseModel
): ApplicationEvent(source)