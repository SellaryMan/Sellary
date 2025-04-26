package org.sellary.sellary.shippedproduct.application.port.dto

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
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
) {
    companion object {
        fun fromDomain(domain: ShippedProduct): ShippedProductDto = with(domain) {
            ShippedProductDto(
                id = id ?: throw IllegalArgumentException("id must not be null, domain: $this"),
                name = name,
                type = type,
                code = code,
                barcode = barcode,
                tags = tags,
                shippedProductExp = shippedProductExp.map(ShippedProductExpDto::fromDomain),
                shippedProductCost = shippedProductCost?.let(ShippedProductCostDto::fromDomain)
            )
        }
    }
}

data class ShippedProductExpDto(
    val id: Long,
    val expDate: LocalDateTime,
    val quantity: Long,
    val manufactureDate: LocalDateTime? = null,
    val lowStockThresholdDay: Long? = null,
    val noShippingThresholdDay: Long? = null
) {
    companion object {
        fun fromDomain(domain: ShippedProductExp): ShippedProductExpDto = with(domain) {
            ShippedProductExpDto(
                id = id ?: throw IllegalArgumentException("id must not be null, domain: $this"),
                expDate = expDate,
                quantity = quantity,
                manufactureDate = manufactureDate,
                lowStockThresholdDay = lowStockThresholdDay,
                noShippingThresholdDay = noShippingThresholdDay
            )
        }
    }
}

data class ShippedProductCostDto(
    val id: Long,
    val unitPurchasePrice: Double? = null,
    val boxPurchasePrice: Double? = null,
    val palletPurchasePrice: Double? = null,
    val unitSellingPrice: Double? = null,
    val boxSellingPrice: Double? = null,
    val palletSellingPrice: Double? = null
) {
    companion object {
        fun fromDomain(domain: ShippedProductCost): ShippedProductCostDto = with(domain) {
            ShippedProductCostDto(
                id = id ?: throw IllegalArgumentException("id must not be null, domain: $this"),
                unitPurchasePrice = unitPurchasePrice,
                boxPurchasePrice = boxPurchasePrice,
                palletPurchasePrice = palletPurchasePrice,
                unitSellingPrice = unitSellingPrice,
                boxSellingPrice = boxSellingPrice,
                palletSellingPrice = palletSellingPrice
            )
        }
    }
}