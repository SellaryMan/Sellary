package org.sellary.sellary.sellingproduct.service.dto

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import java.util.*

data class SellingProductRegisterDto(
    val name: String,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = Collections.emptySet(),
) {
    fun toDomain(): SellingProduct {
        return SellingProduct(
            name = this.name,
            code = this.code,
            barcode = this.barcode,
            tags = this.tags
        )
    }
}
