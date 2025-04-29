package org.sellary.sellary.sellingproduct.service

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.sellary.sellary.sellingproduct.domain.SellingProduct

internal class SellingProductServiceTest {
    private val sellingProductRepository = SellingProductMemoryRepository()
    private val sellingProductService = SellingProductService(sellingProductRepository)

    @Test
    fun findAll() {
        val sellingProduct = SellingProduct(1L, "판매상품1", "AB123", "ASDFBKKDF1")
        sellingProductRepository.register(sellingProduct)
        val sellingProductList = sellingProductService.getSellingProductList()
        assertThat(sellingProductList).hasSize(1)
    }
}