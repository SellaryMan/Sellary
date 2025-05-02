package org.sellary.sellary.sellingproduct.controller

import org.sellary.sellary.sellingproduct.service.SellingProductService
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductRegisterDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductUpdateDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/selling-product")
class SellingProductController(
    private val sellingProductService: SellingProductService
) {

    @GetMapping
    fun getSellingProductList(): List<SellingProductDto> =
        sellingProductService.getSellingProductList()

    @GetMapping("{id}")
    fun getSellingProduct(@PathVariable id : Long): SellingProductDto? =
        sellingProductService.getSellingProductById(id)

    @PostMapping
    fun registerSellingProduct(@RequestBody registerDto : SellingProductRegisterDto) =
        sellingProductService.registerSellingProduct(registerDto)

    @DeleteMapping("{id}")
    fun removeSellingProduct(@PathVariable id : Long) =
        sellingProductService.removeSellingProductById(id)

    @PutMapping
    fun updateSellingProduct(@RequestBody updateDto: SellingProductUpdateDto) =
        sellingProductService.updateSellingProduct(updateDto)
}