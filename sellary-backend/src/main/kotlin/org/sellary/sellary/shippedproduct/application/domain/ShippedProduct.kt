package org.sellary.sellary.shippedproduct.application.domain

import java.util.Collections.emptyList
import java.util.Collections.emptySet

data class ShippedProduct(
    val id: Long? = null,
    val name: String? = null,
    val type: ShippedProductType? = null,
    val code: String? = null,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
    val shippedProductExp: List<ShippedProductExp> = emptyList(),
    val shippedProductCost: ShippedProductCost? = null,
) {
    companion object {
        fun of(shippedProductId: Long): ShippedProduct {
            return ShippedProduct(id = shippedProductId)
        }
    }
}
