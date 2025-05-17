package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct

interface SellingProductRepository {
    fun findAll(): List<SellingProduct>
    fun register(sellingProduct: SellingProduct)
    fun findById(id: Long): SellingProduct
    fun deleteById(id: Long)
    fun update(sellingProduct: SellingProduct)
    fun findByName(it: String): List<SellingProduct>
    fun findByCode(it: String): SellingProduct
}