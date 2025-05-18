package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto

data class SellingShippedProductDto(
    val id: Long? = null,
    val shippedProduct: ShippedProductDto,
    val shippedProductQuantity: Integer? = null
) {
    companion object {
        fun from(product: SellingShippedProduct) : SellingShippedProductDto {
            return SellingShippedProductDto(
                id = product.id,
                shippedProduct = ShippedProductDto.fromDomain(product.shippedProduct!!),
                shippedProductQuantity = product.shippedProductQuantity
            )
        }
    }
}