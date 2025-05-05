package org.sellary.sellary.sellingproduct.domain

data class SellingShippedProduct(
    val id: Long? = null,
    val sellingProduct: SellingProduct,
//    val shippedProduct: ShippedProduct
    val shippedProductQuantity: Integer? = null
)
