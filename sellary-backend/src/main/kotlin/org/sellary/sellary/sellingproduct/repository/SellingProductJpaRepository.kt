package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingProduct
import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SellingProductJpaRepository : JpaRepository<SellingProductEntity, Long> {
    fun findByName(it: String): List<SellingProductEntity>
    fun findByCode(it: String): List<SellingProductEntity>
}