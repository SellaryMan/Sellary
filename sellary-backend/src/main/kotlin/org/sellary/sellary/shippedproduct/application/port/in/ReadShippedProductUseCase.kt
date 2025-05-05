package org.sellary.sellary.shippedproduct.application.port.`in`

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQuery

interface ReadShippedProductUseCase {
    fun readShippedProduct(query: ShippedProductQuery): List<ShippedProductDto>
}