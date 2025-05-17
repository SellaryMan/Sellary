package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import java.util.*

data class SellingProductUpdateDto(
    val id: Long? = null,
    val name: String,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = Collections.emptySet(),
) {
    fun toDomain(): SellingProduct {
        return SellingProduct(
            id = this.id,
            name = this.name,
            code = this.code,
            barcode = this.barcode,
            tags = this.tags
        )
    }
}
