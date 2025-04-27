package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class SellingProductTagEntity(
    val tag: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selling_product_id", nullable = false)
    val sellingProduct: SellingProductEntity? = null,
) : AuditEntity() {
}