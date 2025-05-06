package org.sellary.sellary.company.stub

import org.sellary.sellary.company.application.domain.Company

object CompanyStubFactory {
    fun createCompany(
        id: Long? = 1L,
        name: String = "테스트 회사",
    ) = Company(
        id = id,
        name = name,
    )
}

fun stubCompany() = CompanyStubFactory.createCompany()