package org.sellary.sellary.company.application.port.out

import org.sellary.sellary.company.application.domain.Company

interface CompanyCommandPort {

    /**
     * 주어진 `Company` 객체를 저장합니다.
     *
     * @param company 저장할 회사 도메인
     * @return id 값이 update 된 회사 도메인
     */
    fun save(company: Company): Company
}