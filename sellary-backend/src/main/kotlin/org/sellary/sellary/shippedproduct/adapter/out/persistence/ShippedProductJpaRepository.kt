package org.sellary.sellary.shippedproduct.adapter.out.persistence

import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface ShippedProductJpaRepository : JpaRepository<ShippedProductEntity, Long> {
    fun findByName(name: String): List<ShippedProductEntity>
    fun findByType(type: ShippedProductType): List<ShippedProductEntity>
    fun findByCode(code: String): ShippedProductEntity?
}