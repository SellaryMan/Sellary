package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.repository.SellingProductRepository
import org.sellary.sellary.sellingproduct.repository.SellingShippedProductRepository
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductRegisterDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductUpdateDto
import org.springframework.stereotype.Service

@Service
class SellingProductService (
    private val sellingProductRepository: SellingProductRepository,
    private val sellingShippedProductRepository: SellingShippedProductRepository
        ) {
    fun getSellingProductList(): List<SellingProductDto> {
        val sellingProductList = sellingProductRepository.findAll()
        return sellingProductList.stream()
            .map { product -> SellingProductDto.from(product) }
            .toList()
    }

    fun getSellingProductById(id: Long): SellingProductDto {
        val optional = sellingProductRepository.findById(id)
        if (optional.isEmpty) {
            throw IllegalArgumentException()
        }
        return optional.map { s -> SellingProductDto.from(s) }.get()
    }

    fun registerSellingProduct(registerDto: SellingProductRegisterDto) {
        val sellingProductEntity = sellingProductRepository.register(registerDto.toDomain())
        val sellingShippedProductList =
            registerDto.extreactSellingShippedProductList(sellingProductEntity.id!!)
        sellingShippedProductRepository.register(sellingShippedProductList)
    }

    fun removeSellingProductById(id: Long) {
        sellingProductRepository.deleteById(id)
    }

    fun updateSellingProduct(updateDto: SellingProductUpdateDto) {
        sellingProductRepository.update(updateDto.toDomain())
    }
}