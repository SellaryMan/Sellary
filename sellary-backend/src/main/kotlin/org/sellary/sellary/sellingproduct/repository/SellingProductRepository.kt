package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct

interface SellingProductRepository {
    fun findAll(): List<SellingProduct>

    fun register(sellingProduct: SellingProduct)
}