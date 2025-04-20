package org.sellary.sellary.shippedproduct.application.domain

enum class ShippedProductType(val type: String) {
    INGREDIENT("ingredient"),
    SUB_INGREDIENT("sub_ingredient"),
    PRODUCT("product");

    companion object {
        fun from(type: String): ShippedProductType {
            return entries.firstOrNull { it.type == type }
                ?: throw IllegalArgumentException("Invalid ShippedProductType: $type")
        }
    }
}