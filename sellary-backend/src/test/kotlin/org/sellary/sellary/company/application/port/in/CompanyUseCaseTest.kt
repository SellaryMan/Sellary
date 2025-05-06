package org.sellary.sellary.company.application.port.`in`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.sellary.sellary.company.application.domain.Company
import org.sellary.sellary.company.application.port.dto.CompanyCreateCommand
import org.sellary.sellary.company.application.port.out.CompanyCommandPort
import org.sellary.sellary.company.application.service.CompanyCommandService

class CompanyUseCaseTest {

    private lateinit var createUseCase: CreateCompanyUseCase

    private lateinit var companyCommandPort: CompanyCommandPort

    @BeforeEach
    fun setUp() {
        companyCommandPort = mock()

        createUseCase = CompanyCommandService(companyCommandPort)
    }

    @Nested
    @DisplayName("회사를 개설할 때")
    inner class Create {

        private val createCommand: CompanyCreateCommand = CompanyCreateCommand(name = "Test Company")

        @Test
        @DisplayName("필수 값 만으로 정상 저장 되어야 한다.")
        fun `create company`() {
            //when
            createUseCase.create(createCommand)


            //then
            val company = argumentCaptor<Company>()
            verify(companyCommandPort, times(1)).save(company.capture())

            val capturedCompany = company.firstValue

            assertThat(capturedCompany.name).isEqualTo(createCommand.name)
        }
    }
}
