package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct

data class SellingShippedProductDto(
    val id: Long? = null,
//    val shippedProduct: ShippedProduct
    val shippedProductQuantity: Integer? = null
) {
    companion object {
        fun from(product: SellingShippedProduct) : SellingShippedProductDto {
            return SellingShippedProductDto(
                id = product.id,
                shippedProductQuantity = product.shippedProductQuantity
            )
        }
    }
}