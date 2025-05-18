package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import java.util.Collections.emptyList
import java.util.Collections.emptySet

data class SellingProductDto(
    val id: Long? = null,
    val name: String? = null,
    val code: String? = null,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
    val sellingShippedProductList: List<SellingShippedProductDto> = emptyList()
) {
    companion object {
        fun from(product: SellingProduct) : SellingProductDto {
            return SellingProductDto(
                id = product.id,
                name = product.name,
                code = product.code,
                barcode = product.barcode,
                tags = product.tags,
                sellingShippedProductList = product.sellingShippedProductList.stream()
                    .map { product -> SellingShippedProductDto.from(product) }
                    .toList()
            )
        }
    }
}