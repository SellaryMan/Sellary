package org.sellary.sellary.shippedproduct.application.port.dto

import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

data class ShippedProductQuery(
    val id: Long?,
    val name: String?,
    val type: ShippedProductType?,
    val code: String?,
    val queryType: ShippedProductQueryType?,
)

enum class ShippedProductQueryType {
    ID, NAME, TYPE, CODE, MULTI_CONDITION
}
