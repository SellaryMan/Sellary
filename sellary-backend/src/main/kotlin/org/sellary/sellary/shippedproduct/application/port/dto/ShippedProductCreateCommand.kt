package org.sellary.sellary.shippedproduct.application.port.dto

import jakarta.validation.constraints.NotBlank
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import java.time.LocalDateTime

data class ShippedProductCreateCommand(
    @field:NotBlank
    val name: String,
    val type: ShippedProductType,
    @field:NotBlank
    val code: String,
    val barcode: String? = null,
    val keywords: Set<String>? = null,

    val quantity: Long,
    val expDate: LocalDateTime? = null,
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
        type = this.type,
        code = this.code,
        barcode = this.barcode,
        tags = this.keywords ?: emptySet(),
        shippedProductExp = listOfNotNull(toExpDomain(this)),
        shippedProductCost = toCostDomain(this),
    )

    private fun toExpDomain(shippedProductExpCreateCommand: ShippedProductCreateCommand): ShippedProductExp =
        ShippedProductExp(
            expDate = shippedProductExpCreateCommand.expDate,
            quantity = shippedProductExpCreateCommand.quantity,
            manufactureDate = shippedProductExpCreateCommand.manufactureDate,
            lowStockThresholdDay = shippedProductExpCreateCommand.lowStockThresholdDay,
            noShippingThresholdDay = shippedProductExpCreateCommand.noShippingThresholdDay,
        )


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