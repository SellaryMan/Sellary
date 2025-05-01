package org.sellary.sellary.shippedproduct.application.service

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Service

@Service
class ShippedProductCommandService(
    private val shippedProductCommandPort: ShippedProductCommandPort,
) : CreateShippedProductUseCase {

    override fun create(shippedProductCreateCommand: ShippedProductCreateCommand) {
        val shippedProduct = shippedProductCreateCommand.toDomain()

        shippedProductCommandPort.create(shippedProduct)
    }
}