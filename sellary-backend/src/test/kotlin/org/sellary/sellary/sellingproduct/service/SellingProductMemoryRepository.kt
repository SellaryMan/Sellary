package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.repository.SellingProductRepository
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.util.*

class SellingProductMemoryRepository(
//    private val data : MutableList<SellingProduct> = mutableListOf() 왜 안되는걸까요
    private val data : MutableList<SellingProduct> = ArrayList()
) : SellingProductRepository {
    override fun findAll(): List<SellingProduct> {
        return data;
    }

    override fun register(sellingProduct: SellingProduct) {
        data.add(sellingProduct)
    }

    override fun findById(id: Long): Optional<SellingProduct> {
        return data.stream()
            .filter { s -> s.id == id }
            .findFirst()
    }

    override fun deleteById(id: Long) {
        data.removeIf { s -> s.id == id }
    }

    override fun update(sellingProduct: SellingProduct) {
        data.stream()
            .filter { s -> s.id == sellingProduct.id }
            .findFirst()
            .ifPresent { s -> s.update(sellingProduct) }
    }

}