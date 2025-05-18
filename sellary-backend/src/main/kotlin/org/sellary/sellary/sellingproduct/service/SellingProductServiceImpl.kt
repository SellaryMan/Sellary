package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.controller.SellingProductQuery
import org.sellary.sellary.sellingproduct.controller.SellingProductQueryType.*
import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.repository.SellingProductRepository
import org.sellary.sellary.sellingproduct.repository.SellingShippedProductRepository
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductRegisterDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductUpdateDto
import org.springframework.stereotype.Service

@Service
class SellingProductServiceImpl(
    private val sellingProductRepository: SellingProductRepository,
    private val sellingShippedProductRepository: SellingShippedProductRepository,
): SellingProductService {
    override fun getSellingProductList(query: SellingProductQuery): List<SellingProductDto> =
        when (query.queryType) {
            ID -> query.id.requireNotNull("id")
                .let { sellingProductRepository.findById(it) }
                .let { listOf(it) }
                .mapToDto()

            NAME -> query.name.requireNotNull("name")
                .let { sellingProductRepository.findByName(it) }
                .mapToDto()

            CODE -> query.code.requireNotNull("code")
                .let { sellingProductRepository.findByCode(it) }
                .mapToDto()

            // todo: 컨디션 종류에 맞는 조회 로직 수행 필요
            MULTI_CONDITION -> sellingProductRepository.findAll().mapToDto()

            null -> sellingProductRepository.findAll().mapToDto()
        }

    override fun getSellingProductById(id: Long): SellingProductDto {
        val product = sellingProductRepository.findById(id)
        return SellingProductDto.from(product)
    }

    override fun registerSellingProduct(registerDto: SellingProductRegisterDto) {
        val sellingProduct = sellingProductRepository.register(registerDto.toDomain())
        val sellingShippedProductList =
            registerDto.extreactSellingShippedProductList(sellingProduct.id)
        sellingShippedProductRepository.register(sellingShippedProductList)
    }

    override fun removeSellingProductById(id: Long) {
        sellingProductRepository.deleteById(id)
    }

    override fun updateSellingProduct(updateDto: SellingProductUpdateDto) {
        sellingProductRepository.update(updateDto.toDomain())
    }

    private fun <T : Any> T?.requireNotNull(fieldName: String): T =
        this ?: throw IllegalArgumentException("$fieldName is null")

    private fun List<SellingProduct>.mapToDto(): List<SellingProductDto> =
        map { SellingProductDto.from(it) }
}