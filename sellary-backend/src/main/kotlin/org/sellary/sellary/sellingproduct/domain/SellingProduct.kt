package org.sellary.sellary.sellingproduct.domain

data class SellingProduct(
    val id: Long? = null,
    val name: String,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
) {
    companion object
}