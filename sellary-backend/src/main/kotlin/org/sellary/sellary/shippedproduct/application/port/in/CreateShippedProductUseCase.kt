package org.sellary.sellary.shippedproduct.application.port.`in`

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand

interface CreateShippedProductUseCase {
    fun create(shippedProductCreateCommand: ShippedProductCreateCommand)
}