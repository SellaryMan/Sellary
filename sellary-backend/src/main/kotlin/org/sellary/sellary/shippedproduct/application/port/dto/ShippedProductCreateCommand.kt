package org.sellary.sellary.shippedproduct.application.port.dto

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
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
    fun toDomain() = ShippedProduct(
        name = this.name,
        type = ShippedProductType.from(this.type),
        code = this.code,
        barcode = this.barcode,
        tags = this.keywords ?: emptySet(),
        shippedProductExp = listOfNotNull(toExpDomain(this)),
        shippedProductCost = toCostDomain(this),
    )

    private fun toExpDomain(shippedProductExpCreateCommand: ShippedProductCreateCommand): ShippedProductExp? =
        shippedProductExpCreateCommand.expDate?.let {
            ShippedProductExp(
                expDate = shippedProductExpCreateCommand.expDate,
                quantity = shippedProductExpCreateCommand.quantity,
                manufactureDate = shippedProductExpCreateCommand.manufactureDate,
                lowStockThresholdDay = shippedProductExpCreateCommand.lowStockThresholdDay,
                noShippingThresholdDay = shippedProductExpCreateCommand.noShippingThresholdDay,
            )
        }

    private fun toCostDomain(shippedProductCostCreateCommand: ShippedProductCreateCommand): ShippedProductCost? {
        if (shippedProductCostCreateCommand.unitPurchasePrice == null &&
            shippedProductCostCreateCommand.boxPurchasePrice == null &&
            shippedProductCostCreateCommand.palletPurchasePrice == null &&
            shippedProductCostCreateCommand.unitSellingPrice == null &&
            shippedProductCostCreateCommand.boxSellingPrice == null &&
            shippedProductCostCreateCommand.palletSellingPrice == null
        ) {
            return null
        }

        return ShippedProductCost(
            unitPurchasePrice = shippedProductCostCreateCommand.unitPurchasePrice,
            boxPurchasePrice = shippedProductCostCreateCommand.boxPurchasePrice,
            palletPurchasePrice = shippedProductCostCreateCommand.palletPurchasePrice,
            unitSellingPrice = shippedProductCostCreateCommand.unitSellingPrice,
            boxSellingPrice = shippedProductCostCreateCommand.boxSellingPrice,
            palletSellingPrice = shippedProductCostCreateCommand.palletSellingPrice
        )
    }
}