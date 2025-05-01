package org.sellary.sellary.shippedproduct.adapter.out.persistence

import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Component

@Component
class ShippedProductCommandJPAPort(
    private val shippedProductJpaRepository: ShippedProductJpaRepository
) : ShippedProductCommandPort {
    
    override fun create(shippedProduct: ShippedProduct) {
        shippedProductJpaRepository.save(ShippedProductEntity.fromDomain(shippedProduct))
    }
}

