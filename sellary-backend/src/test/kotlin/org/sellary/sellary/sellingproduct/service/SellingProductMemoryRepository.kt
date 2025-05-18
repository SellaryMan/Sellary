package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.repository.SellingProductRepository

class SellingProductMemoryRepository(
    private val data : MutableList<SellingProduct> = mutableListOf()
) : SellingProductRepository {
    override fun findAll(): List<SellingProduct> {
        return data;
    }

    override fun register(sellingProduct: SellingProduct): SellingProduct {
        sellingProduct.id = data.size + 1L
        data.add(sellingProduct)
        return sellingProduct
    }

    override fun findById(id: Long): SellingProduct {
        return data.stream()
            .filter { s -> s.id == id }
            .findFirst()
            .get()
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

    override fun findByName(it: String): List<SellingProduct> {
        return data.stream()
            .filter { s -> s.name == it }
            .toList()
    }

    override fun findByCode(it: String): List<SellingProduct> {
        return data.stream()
            .filter { s -> s.code == it }
            .toList()
    }

}