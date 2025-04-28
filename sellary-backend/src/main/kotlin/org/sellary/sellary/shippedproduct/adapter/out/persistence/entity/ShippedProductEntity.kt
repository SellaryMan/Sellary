package org.sellary.sellary.shippedproduct.adapter.out.persistence.entity

import jakarta.persistence.*
import org.sellary.sellary.core.out.persistence.AuditEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

@Entity
class ShippedProductEntity(
    val name: String,
    val code: String,
    val barcode: String? = null,

    @Enumerated(EnumType.STRING)
    val type: ShippedProductType,

    @OneToMany(mappedBy = "shippedProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var tagList: MutableList<ShippedProductTagEntity> = mutableListOf(),

    @OneToMany(mappedBy = "shippedProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var shippedProductExpList: MutableList<ShippedProductExpEntity> = mutableListOf(),

    @OneToOne(mappedBy = "shippedProduct", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var shippedProductCost: ShippedProductCostEntity? = null
) : AuditEntity() {
    fun toDomain() =
        ShippedProduct(
            id = id,
            name = name,
            code = code,
            barcode = barcode,
            type = type,
            tags = tagList.map { it.tag }.toSet(),
            shippedProductCost = shippedProductCost?.toDomain(),
            shippedProductExp = shippedProductExpList.map { it.toDomain() }
        )
}
