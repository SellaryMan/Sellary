package org.sellary.sellary.sellingproduct.repository

import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct
import org.sellary.sellary.sellingproduct.entity.SellingShippedProductEntity
import org.sellary.sellary.shippedproduct.adapter.out.persistence.ShippedProductJpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class SellingShippedProductRepositoryImpl(
    private val sellingShippedProductJpaRepository: SellingShippedProductJpaRepository,
    private val sellingProductJpaRepository: SellingProductJpaRepository,
    private val shippedProductJpaRepository: ShippedProductJpaRepository
) : SellingShippedProductRepository {

    override fun register(productList: List<SellingShippedProduct>) {
        val entityList = productList.stream()
            .map { ssp ->
                val sellingProduct =
                    sellingProductJpaRepository.getReferenceById(ssp.sellingProduct?.id!!)
                val shippedProduct =
                    shippedProductJpaRepository.getReferenceById(ssp.shippedProduct?.id!!)
                SellingShippedProductEntity.from(sellingProduct, shippedProduct,
                    ssp.shippedProductQuantity!!)
            }
            .toList()
        sellingShippedProductJpaRepository.saveAll(entityList)
    }

}