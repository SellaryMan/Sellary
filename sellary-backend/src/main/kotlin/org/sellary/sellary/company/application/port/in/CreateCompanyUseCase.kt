package org.sellary.sellary.company.application.port.`in`

import org.sellary.sellary.company.application.port.dto.CompanyCreateCommand

interface CreateCompanyUseCase {
    fun create(command: CompanyCreateCommand)
}