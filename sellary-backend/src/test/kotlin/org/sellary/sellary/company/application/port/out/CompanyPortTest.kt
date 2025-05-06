package org.sellary.sellary.company.application.port.out

import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.sellary.sellary.autoconfigure.PartialDataJpaTest
import org.sellary.sellary.company.application.domain.Company
import org.sellary.sellary.company.stub.stubCompany
import org.sellary.sellary.shippedproduct.adapter.out.persistence.ShippedProductJpaRepository
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import kotlin.test.Test
import kotlin.test.assertEquals

@PartialDataJpaTest(
    entityPackageClasses = [ShippedProductEntity::class],
    repositoryPackageClasses = [ShippedProductJpaRepository::class]
)
class CompanyPortTest {

    @Autowired
    private lateinit var companyCommandPort: CompanyCommandPort

    @Autowired
    private lateinit var em: TestEntityManager

    @Test
    @DisplayName("회사가 정상적으로 저장되어야 한다.")
    fun `save company`() {
        // given
        val company = stubCompany()

        // when
        val savedCompany = companyCommandPort.save(company)

        // then
        val savedCompanyEntity = em.find(Company::class.java, savedCompany.id)

        assertNotNull(savedCompanyEntity)
        assertEquals(savedCompany.id, savedCompanyEntity.id)
        assertEquals(company.name, savedCompanyEntity.name)
    }
}