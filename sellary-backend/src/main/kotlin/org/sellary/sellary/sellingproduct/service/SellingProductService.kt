package org.sellary.sellary.sellingproduct.service

import org.sellary.sellary.sellingproduct.controller.SellingProductQuery
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductRegisterDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductUpdateDto

interface SellingProductService {
    fun getSellingProductList(query: SellingProductQuery): List<SellingProductDto>
    fun getSellingProductById(id: Long): SellingProductDto?
    fun registerSellingProduct(registerDto: SellingProductRegisterDto): Any
    fun removeSellingProductById(id: Long): Any
    fun updateSellingProduct(updateDto: SellingProductUpdateDto): Any
}