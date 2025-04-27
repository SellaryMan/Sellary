package org.sellary.sellary.shippedproduct.adapter.out.persistence

import jakarta.persistence.EntityNotFoundException
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductQueryPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class ShippedProductQueryPortImpl(
    private val jpaRepository: ShippedProductJpaRepository
) : ShippedProductQueryPort {

    override fun findById(id: Long): ShippedProduct =
        jpaRepository.findById(id).unwrapOrThrow { "Product with id $id not found" }.toDomain()

    override fun findByName(name: String): List<ShippedProduct> = jpaRepository.findByName(name).map { it.toDomain() }

    override fun findByType(type: ShippedProductType): List<ShippedProduct> =
        jpaRepository.findByType(type).map { it.toDomain() }

    override fun findByCode(code: String): ShippedProduct =
        jpaRepository.findByCode(code).unwrapOrThrow { "Product with code: $code not found" }.toDomain()

    override fun findAll(): List<ShippedProduct> = jpaRepository.findAll().map { it.toDomain() }

    private fun <T> Optional<T>.unwrapOrThrow(messageProvider: () -> String): T =
        orElseThrow { EntityNotFoundException(messageProvider()) }

    private fun <T : Any> T?.unwrapOrThrow(messageProvider: () -> String): T =
        this ?: throw EntityNotFoundException(messageProvider())
}