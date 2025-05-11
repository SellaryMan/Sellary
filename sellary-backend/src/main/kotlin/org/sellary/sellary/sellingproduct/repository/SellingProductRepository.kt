package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
import java.util.*

interface SellingProductRepository {
    fun findAll(): List<SellingProduct>
    fun register(sellingProduct: SellingProduct) : SellingProductEntity
    fun findById(id: Long): Optional<SellingProduct>
    fun deleteById(id: Long)
    fun update(toDomain: SellingProduct)
}