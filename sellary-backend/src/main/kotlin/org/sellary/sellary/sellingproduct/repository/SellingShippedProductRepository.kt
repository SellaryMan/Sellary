package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct

interface SellingShippedProductRepository {
    fun register(sellingShippedProduct: List<SellingShippedProduct>)
}