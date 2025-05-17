package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.entity.SellingProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SellingProductJpaRepository : JpaRepository<SellingProductEntity, Long> {
    fun findByName(it: String): List<SellingProductEntity>
    fun findByCode(it: String): Optional<SellingProductEntity>
}