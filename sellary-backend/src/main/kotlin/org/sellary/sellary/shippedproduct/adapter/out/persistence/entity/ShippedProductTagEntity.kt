package org.sellary.sellary.shippedproduct.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class ShippedProductTagEntity(
    val tag: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipped_product_id", nullable = false)
    val shippedProductEntity: ShippedProductEntity? = null,
) : AuditEntity() {
}