package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class SellingShippedProductEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selling_product_id")
    val sellingProduct: SellingProductEntity,
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "shipped_product_id")
//    val shippedProduct: ShippedProduct
    val shippedProductQuantity: Integer? = null
) : AuditEntity()
