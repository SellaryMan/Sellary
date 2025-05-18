package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
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

    @Transactional
    override fun register(sellingProduct: SellingProduct): SellingProduct {
        val savedEntity =
            sellingProductJpaRepository.save(SellingProductEntity.from(sellingProduct))
        sellingProduct.id = savedEntity.id
        return sellingProduct
    }

    override fun findById(id: Long): SellingProduct {
        val productEntity = sellingProductJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException() }
        return productEntity.toDomain()
    }

    @Transactional
    override fun deleteById(id: Long) {
        return sellingProductJpaRepository.deleteById(id)
    }

    @Transactional
    override fun update(sellingProduct: SellingProduct) {
        sellingProductJpaRepository.save(SellingProductEntity.from(sellingProduct))
    }

    override fun findByName(it: String): List<SellingProduct> {
        return sellingProductJpaRepository.findByName(it).stream()
            .map { entity -> entity.toDomain() }
            .toList()

    }

    override fun findByCode(it: String): List<SellingProduct> {
        return sellingProductJpaRepository.findByCode(it).stream()
            .map { entity -> entity.toDomain() }
            .toList()
    }

}