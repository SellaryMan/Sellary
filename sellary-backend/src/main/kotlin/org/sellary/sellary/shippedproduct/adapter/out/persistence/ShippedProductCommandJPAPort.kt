package org.sellary.sellary.shippedproduct.adapter.out.persistence

import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductCostEntity
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductExpEntity
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductTagEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.springframework.stereotype.Component

@Component
class ShippedProductCommandJPAPort(
    private val shippedProductJpaRepository: ShippedProductJpaRepository
) : ShippedProductCommandPort {

    override fun create(shippedProduct: ShippedProduct) {
        shippedProductJpaRepository.save(shippedProduct.toEntity())
    }
}

private fun ShippedProduct.toEntity(): ShippedProductEntity {
    return ShippedProductEntity(
        name = this.name,
        type = this.type,
        code = this.code,
        barcode = this.barcode,
        tagList = this.toTagEntity(),
        shippedProductExpList = this.shippedProductExp.map { it.toEntity() }.toMutableList(),
        shippedProductCost = this.shippedProductCost?.toEntity()
    )
}

private fun ShippedProduct.toTagEntity(): MutableList<ShippedProductTagEntity> {
    return this.tags.map { tag ->
        ShippedProductTagEntity(
            tag = tag,
        )
    }.toMutableList()
}

private fun ShippedProductExp.toEntity(): ShippedProductExpEntity {
    return ShippedProductExpEntity(
        expDate = this.expDate,
        quantity = this.quantity,
        manufactureDate = this.manufactureDate,
        lowStockThresholdDay = this.lowStockThresholdDay,
        noShippingThresholdDay = this.noShippingThresholdDay
    )
}

private fun ShippedProductCost.toEntity(): ShippedProductCostEntity {
    return ShippedProductCostEntity(
        unitPurchasePrice = this.unitPurchasePrice,
        boxPurchasePrice = this.boxPurchasePrice,
        palletPurchasePrice = this.palletPurchasePrice,
        unitSellingPrice = this.unitSellingPrice,
        boxSellingPrice = this.boxSellingPrice,
        palletSellingPrice = this.palletSellingPrice
    )
}