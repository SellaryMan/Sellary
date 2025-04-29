package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.repository.SellingProductRepository
import java.util.ArrayList

class SellingProductMemoryRepository(
    private val data : MutableList<SellingProduct> = ArrayList()
) : SellingProductRepository {
    override fun findAll(): List<SellingProduct> {
        return data;
    }

    override fun register(sellingProduct: SellingProduct) {
        data.add(sellingProduct)
    }

}