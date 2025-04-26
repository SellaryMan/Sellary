package org.sellary.sellary.shippedproduct.application.port.dto

import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import java.time.LocalDateTime

data class ShippedProductDto(
    val id: Long,
    val name: String,
    val type: ShippedProductType,
    val code: String,
    val barcode: String? = null,
    val tags: Set<String> = emptySet(),
    val shippedProductExp: List<ShippedProductExpDto> = emptyList(),
    val shippedProductCost: ShippedProductCostDto? = null,
)

data class ShippedProductExpDto(
    val id: Long,
    val expDate: LocalDateTime,
    val quantity: Long,
    val manufactureDate: LocalDateTime? = null,
    val lowStockThresholdDay: Long? = null,
    val noShippingThresholdDay: Long? = null
)

data class ShippedProductCostDto(
    val id: Long,
    val unitPurchasePrice: Double? = null,
    val boxPurchasePrice: Double? = null,
    val palletPurchasePrice: Double? = null,
    val unitSellingPrice: Double? = null,
    val boxSellingPrice: Double? = null,
    val palletSellingPrice: Double? = null
)