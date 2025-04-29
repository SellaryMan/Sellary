package org.sellary.sellary.sellingproduct.domain

import java.util.Collections.emptyList
import java.util.Collections.emptySet

data class SellingProduct(
    val id: Long? = null,
    val name: String,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
    val sellingShippedProductList: List<SellingShippedProduct> = emptyList()
) {
    companion object
}