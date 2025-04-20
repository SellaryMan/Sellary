package org.sellary.sellary.shippedproduct.application.port.dto

import java.time.LocalDateTime

data class ShippedProductCreateCommand(
    val name: String,
    val quantity: Long,
    val type: String,
    val code: String,
    val barcode: String? = null,
    val keywords: Set<String>? = null,

    val expDate: LocalDateTime? = null,
    val expQuantity: Long? = null,
    val manufactureDate: LocalDateTime? = null,
    val lowStockThresholdDay: Long? = null,
    val noShippingThresholdDay: Long? = null,

    val unitPurchasePrice: Double? = null,
    val boxPurchasePrice: Double? = null,
    val palletPurchasePrice: Double? = null,
    val unitSellingPrice: Double? = null,
    val boxSellingPrice: Double? = null,
    val palletSellingPrice: Double? = null
) {
}
