package org.sellary.sellary.adaptor.out.persistence

import org.sellary.sellary.adaptor.out.persistence.entity.CompanyEntity
import org.sellary.sellary.company.application.domain.Company
import org.sellary.sellary.company.application.port.out.CompanyCommandPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CompanyCommandJpaPort(
    private val jpaRepository: CompanyJpaRepository
) : CompanyCommandPort {

    @Transactional
    override fun save(company: Company): Company {
        val companyEntity = CompanyEntity.fromDomain(company)
        jpaRepository.save(companyEntity)
        return company.copy(id = companyEntity.id)
    }
}