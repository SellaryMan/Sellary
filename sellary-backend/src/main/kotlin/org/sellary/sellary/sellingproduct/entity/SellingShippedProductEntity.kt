package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity
import org.sellary.sellary.sellingproduct.domain.SellingShippedProduct
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity

@Entity
class SellingShippedProductEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selling_product_id")
    val sellingProduct: SellingProductEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipped_product_id")
    val shippedProduct: ShippedProductEntity,
    val shippedProductQuantity: Integer? = null
) : AuditEntity() {
    fun toDomain() : SellingShippedProduct {
        return SellingShippedProduct(
            id = this.id,
            sellingProduct = this.sellingProduct.toDomain(),
            shippedProductQuantity = this.shippedProductQuantity
        )
    }
    companion object {
        fun from(
            sellingProduct: SellingProductEntity,
            shippedProduct: ShippedProductEntity,
            shippedProductQuantity: Integer,
        ): SellingShippedProductEntity {
            return SellingShippedProductEntity(
                sellingProduct = sellingProduct,
                shippedProduct = shippedProduct,
                shippedProductQuantity = shippedProductQuantity
            )
        }
    }
}
