package org.sellary.sellary.shippedproduct.adapter.out.persistence

import jakarta.persistence.EntityNotFoundException
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductQueryPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class ShippedProductQueryJPAPort(
    private val jpaRepository: ShippedProductJpaRepository
) : ShippedProductQueryPort {

    @Transactional(readOnly = true)
    override fun findById(id: Long): ShippedProduct =
        jpaRepository.findById(id).unwrapOrThrow { "Product with id $id not found" }.toDomain()

    @Transactional(readOnly = true)
    override fun findByName(name: String): List<ShippedProduct> =
        jpaRepository.findByNameFetchAll(name).map { it.toDomain() }

    @Transactional(readOnly = true)
    override fun findByType(type: ShippedProductType): List<ShippedProduct> =
        jpaRepository.findByTypeFetchAll(type).map { it.toDomain() }

    @Transactional(readOnly = true)
    override fun findByCode(code: String): ShippedProduct =
        jpaRepository.findByCodeFetchAll(code).unwrapOrThrow { "Product with code: $code not found" }.toDomain()

    @Transactional(readOnly = true)
    override fun findAll(): List<ShippedProduct> =
        jpaRepository.findAll().map { it.toDomain() }

    @Transactional(readOnly = true)
    override fun existsByCode(code: String?): Boolean =
        jpaRepository.existsByCode(code)

    private fun <T> Optional<T>.unwrapOrThrow(messageProvider: () -> String): T =
        orElseThrow { EntityNotFoundException(messageProvider()) }

    private fun <T : Any> T?.unwrapOrThrow(messageProvider: () -> String): T =
        this ?: throw EntityNotFoundException(messageProvider())
}