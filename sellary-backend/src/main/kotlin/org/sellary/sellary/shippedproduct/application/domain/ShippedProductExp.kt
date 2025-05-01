package org.sellary.sellary.shippedproduct.application.domain

import java.time.LocalDateTime

data class ShippedProductExp(
    val id: Long? = null,
    val expDate: LocalDateTime,
    val quantity: Long,
    val manufactureDate: LocalDateTime? = null,
    val lowStockThresholdDay: Long? = null,
    val noShippingThresholdDay: Long? = null
)