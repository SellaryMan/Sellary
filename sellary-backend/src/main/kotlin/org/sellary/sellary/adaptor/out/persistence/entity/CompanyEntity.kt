package org.sellary.sellary.adaptor.out.persistence.entity

import jakarta.persistence.Entity
import org.sellary.sellary.company.application.domain.Company
import org.sellary.sellary.core.out.persistence.AuditEntity

@Entity
class CompanyEntity(
    val name: String?,
) : AuditEntity() {
    companion object {
        fun fromDomain(company: Company) =
            CompanyEntity(
                name = company.name,
            )
    }
}