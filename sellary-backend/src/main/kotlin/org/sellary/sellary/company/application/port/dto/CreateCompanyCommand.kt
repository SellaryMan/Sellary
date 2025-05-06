package org.sellary.sellary.company.application.port.dto

import org.sellary.sellary.company.application.domain.Company

data class CreateCompanyCommand(
    val name: String,
) {
    fun toDomain(): Company =
        Company(
            name = name,
        )
}
