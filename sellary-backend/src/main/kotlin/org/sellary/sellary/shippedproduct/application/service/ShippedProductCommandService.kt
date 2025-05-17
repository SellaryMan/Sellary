package org.sellary.sellary.shippedproduct.application.service

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.DeleteShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductQueryPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShippedProductCommandService(
    private val shippedProductCommandPort: ShippedProductCommandPort,
    private val shippedProductQueryPort: ShippedProductQueryPort,
) : CreateShippedProductUseCase, DeleteShippedProductUseCase {

    @Transactional
    override fun create(shippedProductCreateCommand: ShippedProductCreateCommand) {
        val shippedProduct = shippedProductCreateCommand.toDomain()

        verifyDuplicateCode(shippedProduct.code)
        shippedProductCommandPort.create(shippedProduct)
    }

    @Transactional
    override fun delete(id: Long) {
        shippedProductCommandPort.delete(id)
    }

    private fun verifyDuplicateCode(code: String?) {
        if (shippedProductQueryPort.existsByCode(code)) {
            throw IllegalArgumentException("이미 등록된 출고상품 코드입니다.: $code")
        }
    }
}