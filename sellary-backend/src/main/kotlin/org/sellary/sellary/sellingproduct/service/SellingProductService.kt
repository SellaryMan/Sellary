package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.repository.SellingProductRepository
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.springframework.stereotype.Service

@Service
class SellingProductService (
    private val sellingProductRepository: SellingProductRepository
        ) {
    fun getSellingProductList(): List<SellingProductDto> {
        val sellingProductList = sellingProductRepository.findAll()
        return sellingProductList.stream()
            .map { product -> SellingProductDto.from(product) }
            .toList()
    }
}