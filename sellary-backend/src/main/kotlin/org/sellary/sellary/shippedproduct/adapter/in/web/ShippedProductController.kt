package org.sellary.sellary.shippedproduct.adapter.`in`.web

import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/shipped-product")
class ShippedProductController(
    val createUseCase: CreateShippedProductUseCase,
) {
    @PostMapping
    fun foo(@RequestBody createShippedProductCommand: ShippedProductCreateCommand) {
        createUseCase.create(createShippedProductCommand)
    }
}