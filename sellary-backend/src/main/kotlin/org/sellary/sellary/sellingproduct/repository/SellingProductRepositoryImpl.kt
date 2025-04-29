package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class SellingProductRepositoryImpl(
    private val sellingProductJpaRepository: SellingProductJpaRepository
) : SellingProductRepository {
    override fun findAll(): List<SellingProduct> {
        return sellingProductJpaRepository.findAll().stream()
            .map { entity -> entity.toDomain() }
            .toList()
    }

    override fun register(sellingProduct: SellingProduct) {
        sellingProductJpaRepository.save(SellingProductEntity.from(sellingProduct))
    }

}