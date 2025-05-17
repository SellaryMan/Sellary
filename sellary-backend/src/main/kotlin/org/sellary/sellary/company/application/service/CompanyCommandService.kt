package org.sellary.sellary.company.application.service

import org.sellary.sellary.company.application.port.dto.CompanyCreateCommand
import org.sellary.sellary.company.application.port.`in`.CreateCompanyUseCase
import org.sellary.sellary.company.application.port.out.CompanyCommandPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompanyCommandService(
    private val companyCommandPort: CompanyCommandPort
) : CreateCompanyUseCase {

    @Transactional
    override fun create(command: CompanyCreateCommand) {
        val company = command.toDomain()
        companyCommandPort.save(company)
    }
}