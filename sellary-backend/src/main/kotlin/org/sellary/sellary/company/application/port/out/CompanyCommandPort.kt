package org.sellary.sellary.company.application.port.out

import org.sellary.sellary.company.application.domain.Company

interface CompanyCommandPort {
    fun save(company: Company)
}