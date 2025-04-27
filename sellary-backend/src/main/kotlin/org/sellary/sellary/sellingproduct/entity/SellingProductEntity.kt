package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class SellingProductEntity(
    val name: String,
    val code: String,
    val barcode: String? = null,

    @OneToMany(mappedBy = "sellingProduct", fetch = FetchType.LAZY)
    val tags: Set<SellingProductTagEntity> = emptySet(),
    @OneToMany(mappedBy = "sellingProduct", fetch = FetchType.LAZY)
    val sellingShippedProductList: List<SellingShippedProductEntity> = emptyList()
) : AuditEntity()