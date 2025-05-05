package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import org.sellary.sellary.core.out.persistence.AuditEntity
import org.sellary.sellary.sellingproduct.domain.SellingProduct
import java.util.Collections.emptyList
import java.util.Collections.emptySet
import java.util.stream.Collectors

@Entity
class SellingProductEntity(
    val name: String,
    val code: String,
    val barcode: String? = null,

    @OneToMany(mappedBy = "sellingProduct", fetch = FetchType.LAZY)
    var tags: Set<SellingProductTagEntity> = emptySet(),
    @OneToMany(mappedBy = "sellingProduct", fetch = FetchType.LAZY)
    val sellingShippedProductList: List<SellingShippedProductEntity> = emptyList()
) : AuditEntity() {
    fun toDomain() : SellingProduct {
        return SellingProduct(
            id = this.id,
            name = this.name,
            code = this.code,
            barcode = this.barcode,
            tags = this.tags.stream()
                .map { entity -> entity.toDomain() }
                .collect(Collectors.toSet()),
            sellingShippedProductList = this.sellingShippedProductList.stream()
                .map { entity -> entity.toDomain() }
                .toList()
        )
    }
    companion object {
        fun from(sellingProduct: SellingProduct): SellingProductEntity {
            val sellingProductEntity = SellingProductEntity(
                name = sellingProduct.name,
                code = sellingProduct.code,
                barcode = sellingProduct.barcode
            )
            sellingProductEntity.tags = sellingProduct.tags.stream()
                .map { t -> SellingProductTagEntity.from(t, sellingProductEntity) }
                .collect(Collectors.toSet())
            return sellingProductEntity
        }
    }
}