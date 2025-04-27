package org.sellary.sellary.shippedproduct.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import java.time.LocalDateTime

@Entity
class ShippedProductExpEntity(
    val expDate: LocalDateTime,
    val quantity: Long,
    val manufactureDate: LocalDateTime? = null,
    val lowStockThresholdDay: Long? = null,
    val noShippingThresholdDay: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipped_product_id")
    val shippedProductEntity: ShippedProductEntity? = null,
) : AuditEntity() {
    fun toDomain(): ShippedProductExp =
        ShippedProductExp(
            id = id,
            expDate = expDate,
            quantity = quantity,
            manufactureDate = manufactureDate,
            lowStockThresholdDay = lowStockThresholdDay,
            noShippingThresholdDay = noShippingThresholdDay,
        )
}