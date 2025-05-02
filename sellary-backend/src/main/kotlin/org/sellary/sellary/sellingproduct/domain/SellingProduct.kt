package org.sellary.sellary.sellingproduct.domain

import java.util.Collections.emptyList
import java.util.Collections.emptySet

data class SellingProduct(
    val id: Long? = null,
    var name: String,
    var code: String,
    var barcode: String? = null,
    var tags: Set<String> = emptySet(),
    var sellingShippedProductList: List<SellingShippedProduct> = emptyList()
) {
    fun update(sellingProduct: SellingProduct) {
        this.name = sellingProduct.name
        this.code = sellingProduct.code
        this.barcode = sellingProduct.barcode ?: this.barcode
        this.tags = sellingProduct.tags
    }

    companion object
}