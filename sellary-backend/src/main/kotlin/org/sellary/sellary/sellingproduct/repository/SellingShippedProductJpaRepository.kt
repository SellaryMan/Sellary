package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.entity.SellingShippedProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SellingShippedProductJpaRepository : JpaRepository<SellingShippedProductEntity, Long>