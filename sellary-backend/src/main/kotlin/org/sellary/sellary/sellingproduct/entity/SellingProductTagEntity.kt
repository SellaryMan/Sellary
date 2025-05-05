package org.sellary.sellary.sellingproduct.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class SellingProductTagEntity(
    var tag: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selling_product_id", nullable = false)
    val sellingProduct: SellingProductEntity? = null,
) : AuditEntity() {
    fun toDomain() : String {
        return tag
    }
    companion object {
        fun from(s: String, sellingProduct: SellingProductEntity): SellingProductTagEntity {
            return SellingProductTagEntity(
                tag = s,
                sellingProduct = sellingProduct
            )
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SellingProductTagEntity) return false

        return tag == other.tag
    }

    override fun hashCode(): Int {
        return tag.hashCode()
    }
}