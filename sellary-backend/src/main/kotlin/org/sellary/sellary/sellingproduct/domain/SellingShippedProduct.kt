package org.sellary.sellary.sellingproduct.domain

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct

data class SellingShippedProduct(
    val id: Long? = null,
    val sellingProduct: SellingProduct? = null,
    val shippedProduct: ShippedProduct? = null,
    val shippedProductQuantity: Integer? = null
) {
    companion object {
        fun from(
            sellingProductId: Long,
            shippedProductId: Long,
            quantity: Integer,
        ): SellingShippedProduct {
            return SellingShippedProduct(
                sellingProduct = SellingProduct.of(sellingProductId),
                shippedProduct = ShippedProduct.of(shippedProductId),
                shippedProductQuantity = quantity
            )
        }
    }
}