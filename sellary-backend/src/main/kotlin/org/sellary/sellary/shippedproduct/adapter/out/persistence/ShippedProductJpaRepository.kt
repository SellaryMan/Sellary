package org.sellary.sellary.shippedproduct.adapter.out.persistence

import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface ShippedProductJpaRepository : JpaRepository<ShippedProductEntity, Long> {

    @Query(
        """
       select p from ShippedProductEntity p
       join fetch p.shippedProductCost
       join fetch p.shippedProductExpList
       where p.name = :name
       """
    )
    fun findByNameFetchAll(name: String): List<ShippedProductEntity>

    @Query(
        """
       select p from ShippedProductEntity p
       join fetch p.shippedProductCost
       join fetch p.shippedProductExpList
       where p.type = :type
       """
    )
    fun findByTypeFetchAll(type: ShippedProductType): List<ShippedProductEntity>

    @Query(
        """
       select p from ShippedProductEntity p
       join fetch p.shippedProductCost
       join fetch p.shippedProductExpList
       where p.code = :code
       """
    )
    fun findByCodeFetchAll(code: String): ShippedProductEntity?

    fun existsByCode(code: String): Boolean
}