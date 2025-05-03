package org.sellary.sellary.shippedproduct.adapter.out.persistence

import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ShippedProductCommandJPAPort(
    private val shippedProductJpaRepository: ShippedProductJpaRepository
) : ShippedProductCommandPort {

    @Transactional
    override fun create(shippedProduct: ShippedProduct) {
        shippedProductJpaRepository.save(ShippedProductEntity.fromDomain(shippedProduct))
    }

    @Transactional
    override fun delete(id: Long) {
        shippedProductJpaRepository.deleteById(id)
    }
}

