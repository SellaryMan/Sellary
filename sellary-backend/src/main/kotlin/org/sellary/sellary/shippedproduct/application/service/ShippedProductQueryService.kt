package org.sellary.sellary.shippedproduct.application.service

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQuery
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQueryType.*
import org.sellary.sellary.shippedproduct.application.port.`in`.ReadShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductQueryPort
import org.springframework.stereotype.Service

@Service
class ShippedProductQueryService(
    private val shippedProductQueryPort: ShippedProductQueryPort
) : ReadShippedProductUseCase {
    override fun readShippedProduct(query: ShippedProductQuery): List<ShippedProductDto> =
        when (query.queryType) {
            ID -> query.id.requireNotNull("id")
                .let { shippedProductQueryPort.findById(it) }
                .let { listOf(it) }
                .mapToDto()

            NAME -> query.name.requireNotNull("name")
                .let { shippedProductQueryPort.findByName(it) }
                .mapToDto()

            TYPE -> query.type.requireNotNull("type")
                .let { shippedProductQueryPort.findByType(it) }
                .mapToDto()

            CODE -> query.code.requireNotNull("code")
                .let { shippedProductQueryPort.findByCode(it) }
                .let { listOf(it) }
                .mapToDto()

            // todo: 컨디션 종류에 맞는 조회 로직 수행 필요
            MULTI_CONDITION -> shippedProductQueryPort.findAll().mapToDto()

            null -> shippedProductQueryPort.findAll().mapToDto()
        }

    private fun <T : Any> T?.requireNotNull(fieldName: String): T =
        this ?: throw IllegalArgumentException("$fieldName is null")

    private fun List<ShippedProduct>.mapToDto(): List<ShippedProductDto> =
        map { ShippedProductDto.fromDomain(it) }
}