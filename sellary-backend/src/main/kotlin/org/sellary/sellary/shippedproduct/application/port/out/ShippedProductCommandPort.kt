package org.sellary.sellary.shippedproduct.application.port.out

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct

interface ShippedProductCommandPort {
    fun create(shippedProduct: ShippedProduct)
    fun delete(id: Long)
}