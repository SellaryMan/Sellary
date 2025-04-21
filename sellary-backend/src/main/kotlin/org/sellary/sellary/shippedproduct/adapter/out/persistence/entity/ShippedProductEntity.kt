package org.sellary.sellary.shippedproduct.adapter.out.persistence.entity

import jakarta.persistence.*
import org.sellary.sellary.core.out.persistence.AuditEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

@Entity
class ShippedProductEntity(
    val name: String,
    val code: String,
    val barcode: String? = null,

    @Enumerated(EnumType.STRING)
    val type: ShippedProductType,

    @OneToMany(mappedBy = "shippedProduct", fetch = FetchType.LAZY)
    val tagList: List<ShippedProductTagEntity>? = null,

    @OneToMany(mappedBy = "shippedProduct", fetch = FetchType.LAZY)
    val shippedProductExpList: List<ShippedProductExpEntity>? = null,

    @OneToOne(mappedBy = "shippedProduct", fetch = FetchType.LAZY)
    val shippedProductCost: ShippedProductCostEntity? = null,
) : AuditEntity() {
}
