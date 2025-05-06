package org.sellary.sellary.adaptor.out.persistence

import org.sellary.sellary.adaptor.out.persistence.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface CompanyJpaRepository : JpaRepository<CompanyEntity, String> {
}