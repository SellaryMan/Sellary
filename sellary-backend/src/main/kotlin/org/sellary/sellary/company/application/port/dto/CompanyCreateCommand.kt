package org.sellary.sellary.company.application.port.dto

import jakarta.validation.constraints.NotBlank
import org.sellary.sellary.company.application.domain.Company

data class CompanyCreateCommand(
    @field:NotBlank
    val name: String,
) {
    fun toDomain(): Company =
        Company(
            name = name,
        )
}
