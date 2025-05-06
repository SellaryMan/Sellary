package org.sellary.sellary.company.application.port.`in`

import org.sellary.sellary.company.application.port.dto.CreateCompanyCommand

interface CreateCompanyUseCase {
    fun create(command: CreateCompanyCommand)
}