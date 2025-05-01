package org.sellary.sellary.shippedproduct.application.domain

data class ShippedProduct(
    val id: Long? = null,
    val name: String,
    val type: ShippedProductType,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
    val shippedProductExp: List<ShippedProductExp> = emptyList(),
    val shippedProductCost: ShippedProductCost? = null,
)
