package org.sellary.sellary.shippedproduct.adapter.`in`.web

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQuery
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.ReadShippedProductUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shipped-product")
class ShippedProductController(
    private val createUseCase: CreateShippedProductUseCase,
    private val readUseCase: ReadShippedProductUseCase,
) {
    @PostMapping
    fun createShippedProduct(@RequestBody createShippedProductCommand: ShippedProductCreateCommand) =
        createUseCase.create(createShippedProductCommand)

    @GetMapping

    fun getShippedProduct(@ModelAttribute query: ShippedProductQuery): List<ShippedProductDto> =
        readUseCase.readShippedProduct(query)
}