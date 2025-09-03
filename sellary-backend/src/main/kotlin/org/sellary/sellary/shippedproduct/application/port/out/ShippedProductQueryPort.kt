package org.sellary.sellary.shippedproduct.application.port.out

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

interface ShippedProductQueryPort {
    fun findById(id: Long): ShippedProduct
    fun findByName(name: String): List<ShippedProduct>
    fun findByType(type: ShippedProductType): List<ShippedProduct>
    fun findByCode(code: String): ShippedProduct
    fun findAll(): List<ShippedProduct>
    fun existsByCode(code: String?): Boolean
}