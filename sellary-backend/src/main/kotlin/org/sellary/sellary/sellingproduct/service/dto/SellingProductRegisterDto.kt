package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct
import java.util.*

data class SellingProductRegisterDto(
    val name: String,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = Collections.emptySet(),
    val shippedProductList: List<ShippedProductRegisterDto>,
) {
    data class ShippedProductRegisterDto(
        val shippedProductId: Long,
        val quantity: Integer,

    ) {

    }

    fun toDomain(): SellingProduct {
        return SellingProduct(
            name = this.name,
            code = this.code,
            barcode = this.barcode,
            tags = this.tags,
        )
    }

    fun extreactSellingShippedProductList(sellingProductId: Long?) : List<SellingShippedProduct> {
        return shippedProductList.stream()
            .map { s -> SellingShippedProduct.from(sellingProductId, s.shippedProductId, s.quantity) }
            .toList()
    }
}
