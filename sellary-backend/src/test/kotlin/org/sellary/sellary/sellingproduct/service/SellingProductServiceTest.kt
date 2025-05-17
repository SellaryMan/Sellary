package org.sellary.sellary.sellingproduct.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sellary.sellary.sellingproduct.domain.SellingProduct

internal class SellingProductServiceTest {
    private val sellingProductRepository = SellingProductMemoryRepository()
    private val sellingProductService = SellingProductService(sellingProductRepository)

    @Test
    fun findAll() {
        val sellingProduct = SellingProduct(1L, "판매상품1", "AB123", "ASDFBKKDF1")
        sellingProductRepository.register(sellingProduct)
        val sellingProductList = sellingProductService.getSellingProductList(query)
        assertThat(sellingProductList).hasSize(1)
    }

    @Test
    fun findOne() {
        val sellingProduct = SellingProduct(1L, "판매상품1", "AB123", "ASDFBKKDF1")
        sellingProductRepository.register(sellingProduct)
        val sellingProduct1 = sellingProductService.getSellingProductById(1L)
        assertThat(sellingProduct1.id).isEqualTo(sellingProduct.id)
        assertThat(sellingProduct1.name).isEqualTo(sellingProduct.name)
        assertThat(sellingProduct1.code).isEqualTo(sellingProduct.code)
        assertThat(sellingProduct1.barcode).isEqualTo(sellingProduct.barcode)
//        assertThatThrownBy { sellingProductService.getSellingProductById(2L) }
//            .isExactlyInstanceOf(IllegalArgumentException::class.java) 왜 안됨? java 빨간줄
    }
}