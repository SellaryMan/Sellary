package org.sellary.sellary.shippedproduct.adapter.out.persistence.entity

import jakarta.persistence.Entity
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class ShippedProductCostEntity(
    val unitPurchasePrice: Double? = null,
    val boxPurchasePrice: Double? = null,
    val palletPurchasePrice: Double? = null,
    val unitSellingPrice: Double? = null,
    val boxSellingPrice: Double? = null,
    val palletSellingPrice: Double? = null
) : AuditEntity() {
}