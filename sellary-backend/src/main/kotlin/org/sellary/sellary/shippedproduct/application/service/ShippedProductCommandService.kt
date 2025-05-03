package org.sellary.sellary.shippedproduct.application.service

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.DeleteShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShippedProductCommandService(
    private val shippedProductCommandPort: ShippedProductCommandPort,
) : CreateShippedProductUseCase, DeleteShippedProductUseCase {

    @Transactional
    override fun create(shippedProductCreateCommand: ShippedProductCreateCommand) {
        val shippedProduct = shippedProductCreateCommand.toDomain()

        shippedProductCommandPort.create(shippedProduct)
    }

    @Transactional
    override fun delete(id: Long) {
        shippedProductCommandPort.delete(id)
    }
}