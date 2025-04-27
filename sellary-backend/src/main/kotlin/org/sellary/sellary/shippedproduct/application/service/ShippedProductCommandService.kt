package org.sellary.sellary.shippedproduct.application.service

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Service

@Service
class ShippedProductCommandService(
    private val shippedProductCommandPort: ShippedProductCommandPort,
) : CreateShippedProductUseCase {

    override fun create(shippedProductCreateCommand: ShippedProductCreateCommand) {
        val shippedProduct = ShippedProduct.fromDto(shippedProductCreateCommand)

        shippedProductCommandPort.create(shippedProduct)
    }
}

private fun ShippedProduct.Companion.fromDto(shippedProductCreateCommand: ShippedProductCreateCommand): ShippedProduct {
    return ShippedProduct(
        name = shippedProductCreateCommand.name,
        type = ShippedProductType.from(shippedProductCreateCommand.type),
        code = shippedProductCreateCommand.code,
        barcode = shippedProductCreateCommand.barcode,
        tags = shippedProductCreateCommand.keywords ?: emptySet(),
        shippedProductExp = listOfNotNull(ShippedProductExp.fromDto(shippedProductCreateCommand)),
        shippedProductCost = ShippedProductCost.fromDto(shippedProductCreateCommand),
    )

}

private fun ShippedProductExp.Companion.fromDto(shippedProductExpCreateCommand: ShippedProductCreateCommand): ShippedProductExp? {
    return shippedProductExpCreateCommand.expDate?.let {
        ShippedProductExp(
            expDate = shippedProductExpCreateCommand.expDate,
            quantity = shippedProductExpCreateCommand.quantity,
            manufactureDate = shippedProductExpCreateCommand.manufactureDate,
            lowStockThresholdDay = shippedProductExpCreateCommand.lowStockThresholdDay,
            noShippingThresholdDay = shippedProductExpCreateCommand.noShippingThresholdDay,
        )
    }
}

private fun ShippedProductCost.Companion.fromDto(shippedProductCostCreateCommand: ShippedProductCreateCommand): ShippedProductCost? {
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