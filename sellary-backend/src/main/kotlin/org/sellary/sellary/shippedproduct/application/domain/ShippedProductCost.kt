package org.sellary.sellary.shippedproduct.application.domain

data class ShippedProductCost(
    val id: Long = 0,
    val unitPurchasePrice: Double? = null,
    val boxPurchasePrice: Double? = null,
    val palletPurchasePrice: Double? = null,
    val unitSellingPrice: Double? = null,
    val boxSellingPrice: Double? = null,
    val palletSellingPrice: Double? = null
) {
    companion object
}