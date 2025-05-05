package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

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
    override fun register(sellingProduct: SellingProduct) {
        sellingProductJpaRepository.save(SellingProductEntity.from(sellingProduct))
    }

    override fun findById(id: Long): Optional<SellingProduct> {
        return sellingProductJpaRepository.findById(id)
            .map { entity -> entity.toDomain() }
    }

    @Transactional
    override fun deleteById(id: Long) {
        return sellingProductJpaRepository.deleteById(id)
    }

    @Transactional
    override fun update(sellingProduct: SellingProduct) {
        sellingProductJpaRepository.save(SellingProductEntity.from(sellingProduct))
    }

}