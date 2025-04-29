package org.sellary.sellary.sellingproduct.controller

import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.SellingProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/selling-product")
class SellingProductController(
    private val sellingProductService: SellingProductService
) {

    @GetMapping
    fun getSellingProductList(): List<SellingProductDto> =
        sellingProductService.getSellingProductList()
}